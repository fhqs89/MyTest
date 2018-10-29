package com.example.demo.interview;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

	
	
	public static Lock lockA = new ReentrantLock();
	public static Lock lockB = new ReentrantLock();
	public static Lock lockC = new ReentrantLock();
	
	private static Thread tA = new Thread(()->{
		for(int i=0;i<10;i++) {
			lockB.lock();
			lockC.lock();
			print("A");
			
		}
		
	});
	
	private static Thread tB = new Thread(()->{
		for(int i=0;i<10;i++) {
			print("B");
		}
		
	});
	
	private static Thread tC = new Thread(()->{
		for(int i=0;i<10;i++) {
			print("C");
		}
		
	});
	
	public static void main(String[] args) {
		

	}
	
	public static void print(String str) {
		System.out.println(str);
	}

}
