package com.example.demo.net;

import java.util.Scanner;

public class NioClient {
	
	private static NioClientHandle nioClientHandle;

	public static void start() {
		if(nioClientHandle != null) {
			nioClientHandle.stop();
		}
		nioClientHandle = new NioClientHandle("127.0.0.1",1234);
		new Thread(nioClientHandle, "client").start();
	}
	
	public static boolean sendMsg(String msg) throws Exception {
		nioClientHandle.sendMsg(msg);
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		start();
		Scanner scanner = new Scanner(System.in);
		while(NioClient.sendMsg(scanner.next()));
	}

}
