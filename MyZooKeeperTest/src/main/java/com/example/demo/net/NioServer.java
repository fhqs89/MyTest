package com.example.demo.net;

public class NioServer {

	private static NioServerHandle nioServerHandle;
	
	public static void start() {
		if(nioServerHandle != null) {
			nioServerHandle.stop();
			nioServerHandle = new NioServerHandle("127.0.0.1", 12345);
			new Thread(nioServerHandle, "Server").start();
		}
	}
	
	public static void main(String[] args) {
		start();
	}

}
