package com.example.demo.io.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class SocketNIOTest01 {

	public static void main(String[] args) {
		
		try {
			SocketAddress rama = new InetSocketAddress("rama.poly.edu",19);
			SocketChannel client = SocketChannel.open(rama);
//			client.configureBlocking(false);  //设置为非阻塞模式  
			
			ByteBuffer buffer = ByteBuffer.allocate(74);
			WritableByteChannel out = Channels.newChannel(System.out);
			
			while(client.read(buffer) != -1) {
				buffer.flip();
				out.write(buffer);
				buffer.clear();
			}
			
			//下面是非阻塞模式的循环
//			while(true) {
//				//把每次循环都要运行的代码都放在这里
//				//无论有没有读取到数据
//				int n = client.read(buffer);
//				if(n > 0) {
//					buffer.flip();
//					out.write(buffer);
//					buffer.clear();
//				} else if(n == -1) {
//					//这不应当发生，除非服务器发生故障
//					break;
//				}
//				
//				
//			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
