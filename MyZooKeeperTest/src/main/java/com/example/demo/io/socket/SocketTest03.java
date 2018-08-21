package com.example.demo.io.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class SocketTest03 {

	public static void main(String[] args) {
		
		try {
//			InetAddress address = InetAddress.getByName("127.0.0.1");
//			Socket socket = new Socket("127.0.0.1",8080,address,0);
//			System.out.println("socket connected");
//			socket.close();
			
			
			Socket sock = new Socket(); //未连接的socket
			SocketAddress adds = new InetSocketAddress("127.0.0.1",8089);
			sock.connect(adds, 5000);
			System.out.println("sock connected");
//			sock.setSoLinger(true, 10);
//			System.out.println("sock connected "+ sock.getSoLinger());
			sock.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
