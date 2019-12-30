package com.example.demo.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServerHandle implements Runnable{

	private ServerSocketChannel socketChannel;
	private Selector selector;
	
	private boolean started;
	
	public NioServerHandle(int port) {
		try {
			selector = Selector.open();
			socketChannel = ServerSocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.socket().bind(new InetSocketAddress(port));
			socketChannel.register(selector, SelectionKey.OP_ACCEPT);
			started = true;
			System.out.println("服务器已启动，端口号："+port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void stop() {
		started = false;
	}
	
	@Override
	public void run() {
		while(started) {
			try {
				selector.select();
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				while(it.hasNext()) {
					SelectionKey key = it.next();
					it.remove();
					try {
						handle(key);
					} catch (Exception e) {
						e.printStackTrace();
						if(key != null) {
							key.cancel();
							if(key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void handle(SelectionKey key) throws IOException {
		if(key.isValid()) {
			if(key.isAcceptable()) {
				ServerSocketChannel ch = (ServerSocketChannel) key.channel();
				SocketChannel sc = ch.accept();
				sc.configureBlocking(false);
				System.out.println("======socket channel 建立连接");
				sc.register(selector, SelectionKey.OP_READ);
			}
			if(key.isReadable()) {
				System.out.println("======socket channel 数据准备完成，"+ "可以去读==读取=====");
				SocketChannel ch = (SocketChannel) key.channel();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				int readByte = ch.read(buffer);
				if(readByte > 0) {
					buffer.flip();
					byte[] by = new byte[buffer.remaining()];
					buffer.get(by);
					String message = new String(by, "UTF-8");
					System.out.println("服务器收到消息："+ message);
					doWrite(ch,message);
				}
				
			}
		}
	}
	
	public void doWrite(SocketChannel ch, String message) throws IOException {
		byte[] by = message.getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put(by);
		buffer.flip();
		ch.write(buffer);
	}
	
	public String response(String msg){
        return "Hello,"+msg+",Now is "+new java.util.Date(
                System.currentTimeMillis()).toString() ;
    }
}
