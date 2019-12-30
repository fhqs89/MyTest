package com.example.demo.nio.test;

import java.io.IOException;
import java.util.Scanner;

public class NioClient {

	public static String host="127.0.0.1";
	public static int port = 12345;
	public static NioClientHandle nioClientHandle;
	public static void start() {
		nioClientHandle = new NioClientHandle(host, port);
		new Thread(nioClientHandle).start();
	}
	public static boolean sendMsg(String msg) throws IOException {
		nioClientHandle.sendMsg(msg);
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		start();
		System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while(sendMsg(scanner.next()));
		
	}

}
