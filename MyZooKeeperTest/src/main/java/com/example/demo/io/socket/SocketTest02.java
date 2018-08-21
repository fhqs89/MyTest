package com.example.demo.io.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest02 {

	public static void main(String[] args) {
		for(int i = 1;i<=4;i++){
			try {
				Socket socket = new Socket("127.0.0.1",1);
				System.out.println("There id a server on port "+1+" of localhost");
				socket.getInputStream();
				socket.close();
			} catch (UnknownHostException e) {
				System.out.println("ex");
				continue;
			} catch (IOException e) {
			}
			
		}
		
		
		
	}

}
