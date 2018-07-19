package com.example.demo.io.thread;

public class IOThreadMain {

	public static void main(String[] args) {

		Thread t1 = new Thread(new IOThreadCallback("D://myTest.txt"));
		Thread t2 = new Thread(new IOThreadCallback("D://myTest.txt",new IOThreadMain()));
		t1.start();
		t2.start();
	}
	
	public static void receiveStaticDigest(String param){
		//在回调函数中处理子线程返回的数据
		String result = param;
		System.out.println("静态方法  --IOThreadMain callback method "+result);
	}

	public void receiveDigest(String param){
		//在回调函数中处理子线程返回的数据  不过是把处理类通过构造函数传进子线程
		String result = param;
		System.out.println("IOThreadMain callback method "+result);
	}
}
