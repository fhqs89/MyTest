package com.example.demo.face;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DemTest01 {

	private static Object lock = new Object();
	static Lock la = new ReentrantLock();
	static Lock lb = new ReentrantLock();
	static volatile int i = 0;
	static String flag = "A";
	
	private static class PrintA implements Runnable{

		@Override
		public void run() {
			while(true) {
				if(i == 99) {
					break;
				}
				synchronized(lock) {
					try {
						if(flag.equals("A")) {
							System.out.print("A");
							flag= "B";
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}
	private static class PrintB implements Runnable{
		@Override
		public void run() {
			while(true) {
				if(i == 100) {
					break;
				}
				synchronized(lock) {
						try {
							if(flag.equals("B")) {
								System.out.print("B");
								flag= "C";
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			}
		}
	}
	private static class PrintC implements Runnable{
		@Override
		public void run() {
			while(true) {
				if(i == 100) {
					break;
				}
				synchronized(lock) {
						try {
							if(flag.equals("C")) {
								i++;
								System.out.print("C");
								flag= "D";
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			}
		}
	}
	
	private static class PrintD implements Runnable{
		@Override
		public void run() {
			while(true) {
				if(i == 100) {
					break;
				}
				synchronized(lock) {
						try {
							if(flag.equals("D")) {
								i++;
								System.out.print("D");
								if(i%10 == 0) {
									System.out.println();
								}
								flag= "A";
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		PrintA a = new PrintA();
		PrintB b = new PrintB();
		PrintC c = new PrintC();
		PrintD d = new PrintD();
		new Thread(a).start();
		new Thread(b).start();
		new Thread(c).start();
		new Thread(d).start();
		
	}

}
