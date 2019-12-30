package com.example.demo.nio.test;

public class NioServer {

	private static int port = 12345;
	private static NioServerHandle server;
	
	public static void start() {
		if(server != null) {
			server.stop();
		}
		server = new NioServerHandle(port);
		new Thread(server).start();
	}
	
	public static void main(String[] args) {
		start();

	}

}
