package com.example.demo.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClientHandle implements Runnable{
	
	private int port;
	private String host;
	private Selector select;
	private SocketChannel socketChannel;
	private boolean started ;
	
	public NioClientHandle(String host,int port) {
		this.port = port;
		this.host = host;
		try {
			select = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			started = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			doConnection();
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(started) {
			try {
				select.select();
				Set<SelectionKey> keys = select.selectedKeys();
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
	
	public void stop() {
		started = false;
	}
	
	public void doConnection() throws ClosedChannelException, IOException {
		//非阻塞模式下，connect操作会返回false，后面会发出CONNECT事件来表示连接
		if(socketChannel.connect(new InetSocketAddress(host, port))) {
		}else {
			socketChannel.register(select, SelectionKey.OP_CONNECT);
		}
	}
	
	public void handle(SelectionKey key) throws IOException {
		if(key.isValid()) {//判断key是否有效
			SocketChannel channel = (SocketChannel) key.channel();
			if(key.isConnectable()) {//连接事件
				if(channel.finishConnect()) {
				}else {
					System.exit(1);
				}
			}
			if(key.isReadable()) {
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				//读取请求码流，返回读取到的字节数
				int readByte = channel.read(buffer);
				if(readByte > 0) {
					buffer.flip();
					byte[] by = new byte[buffer.remaining()];
					buffer.get(by);
					String result = new String(by, "UTF-8");
					System.out.println("accept message:" + result);
				}else {
					key.cancel();
					channel.close();
				}
			}
		}
	}
	
	public void sendMsg(String msg) throws IOException {
		socketChannel.register(select, SelectionKey.OP_READ);
		doWrite(socketChannel, msg);
	}
	
	private void doWrite(SocketChannel channel,String request) throws IOException {
		byte[] by = request.getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(by.length);
		buffer.put(by);
		buffer.flip();
		channel.write(buffer);
	}
}
