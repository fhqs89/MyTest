package com.example.demo.face;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchABCTest {

	private static Object lock = new Object();
	static Lock la = new ReentrantLock();
	static Lock lb = new ReentrantLock();
	private static class PrintA implements Runnable{

		@Override
		public void run() {
			while(true) {
				lb.lock();
				System.out.print("A");
				try {
					lb.unlock();
				}catch(Exception e) {
				}
			}
			
		}
		
	}
	private static class PrintB implements Runnable{
		@Override
		public void run() {
			while(true) {
				try {
					la.unlock();
				}catch(Exception e) {
				}
				System.out.print("B");
				lb.lock();
				
			}
		}
		
	}
	private static class PrintC implements Runnable{

		@Override
		public void run() {
			
		}
		
	}
	private static class PrintD implements Runnable{

		@Override
		public void run() {
			
		}
		
	}
	
	public static void main(String[] args) {
		PrintA a = new PrintA();
		PrintB b = new PrintB();
		a.run();
		b.run();
		
		
		
	}

}
