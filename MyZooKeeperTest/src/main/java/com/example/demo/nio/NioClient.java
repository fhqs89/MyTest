package com.example.demo.nio;

import java.util.Scanner;

public class NioClient {
	
	private static NioClientHandle nioClientHandle;
	
	public static void start() {
		if(nioClientHandle != null) {
			nioClientHandle.stop();
		}
		nioClientHandle = new NioClientHandle("127.0.0.1",12345);
		new Thread(nioClientHandle, "client").start();
	}
	
	public static boolean sendMsg(String msg) throws Exception {
		nioClientHandle.sendMsg(msg);
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		start();
		System.out.println("请输入请求消息2222：");
		Scanner scanner = new Scanner(System.in);
		while(NioClient.sendMsg(scanner.next()));
	}

}
