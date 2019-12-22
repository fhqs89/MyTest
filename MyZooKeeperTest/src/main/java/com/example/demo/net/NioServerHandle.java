package com.example.demo.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServerHandle implements Runnable{
	
	private Selector selector;
	private ServerSocketChannel serverChannel;
	public String ip;
	public int port;
	
	private volatile boolean start = true;
	
	public NioServerHandle(String ip, int port) {
		this.ip = ip;
		this.port = port;
		try {
			selector = Selector.open();
			serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			serverChannel.socket().bind(new InetSocketAddress(port));
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			start = true;
			System.out.println("服务器已启动，端口号："+ port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		start = false;
	}

	@Override
	public void run() {
		while(start) {
			try {
				selector.select();
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey key = null;
				while(it.hasNext()) {
					key = it.next();
					it.remove();
					try {
						handleInput(key);
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
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	private void handleInput(SelectionKey key) throws IOException {
		if(key.isValid()) {
			if(key.isAcceptable()) {
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();//建立一个连接
				System.out.println("======socket channel 建立连接");
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			//读消息
			if(key.isReadable()) {
				System.out.println("======socket channel 数据准备完成，"+ "可以去读==读取=====");
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(buffer);
				if(readBytes > 0) {
					buffer.flip();
					byte[] bytes = new byte[buffer.remaining()];
					buffer.get(bytes);
					String message = new String(bytes, "UTF-8");
					System.out.println("服务器收到消息："+ message);
					String result = response(message);
					doWrite(sc,result);
				}else {
					key.cancel();
					sc.close();
				}
				
			}
		}
		
	}

	private void doWrite(SocketChannel channel, String request) throws IOException {
		byte[] bytes = request.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();
		channel.write(writeBuffer);
	}

	public String response(String msg){
        return "Hello,"+msg+",Now is "+new java.util.Date(
                System.currentTimeMillis()).toString() ;
    }

}
