package com.example.demo.nio;

public class Ch01Const {
	
	public static int PORT = 9000;
	
	public static String IP = "127.0.0.1";
	
	public static String response(String msg) {
		return "Hello," + msg + ",Now is " + new java.util.Date(System.currentTimeMillis());
	}

}
