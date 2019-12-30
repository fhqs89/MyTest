package com.example.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClientHandle implements Runnable{
	
	public String ip;
	public int port;
	private Selector selector;
	private SocketChannel socketChannel;
	
	private volatile boolean start;
	
	public NioClientHandle(String ip, int port) {
		this.ip = ip;
		this.port = port;
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			start = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		start = false;
	}

	@Override
	public void run() {
		try {
			doConnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		while(start) {
			try {
				selector.select();//不断检查当前是否有事件发生（如读写事件），阻塞方法
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		if(key.isValid()) {
			SocketChannel sc = (SocketChannel) key.channel();
			if(key.isConnectable()) {
				if(sc.finishConnect()) {
					//连接完成，不做操作
				}else {
					System.exit(1);
				}
			}
			if(key.isReadable()) {
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				int readByte = sc.read(buffer);
				if(readByte > 0) {
					buffer.flip();
					byte[] bytes = new byte[buffer.remaining()];
					buffer.get(bytes);
					String result = new String(bytes, "UTF-8");
					System.out.println("accept message:" + result);
				}else if(readByte < 0) {
					key.cancel();
					sc.close();
				}
				
			}
		}
		
	}

	public void sendMsg(String msg) throws IOException {
		socketChannel.register(selector, SelectionKey.OP_READ);
		doWrite(socketChannel, msg);
	}
	
	private void doWrite(SocketChannel channel, String request) throws IOException {
		byte[] bytes = request.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();
		channel.write(writeBuffer);
	}

	private void doConnect() throws IOException {
		//非阻塞模式下，connect操作会返回false，后面会发出CONNECT事件来表示连接
		if(socketChannel.connect(new InetSocketAddress(ip,port))) {
		}else {
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

}
