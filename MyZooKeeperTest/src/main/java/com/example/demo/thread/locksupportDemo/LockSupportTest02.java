package com.example.demo.thread.locksupportDemo;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest02 {
	
	public static Object u = new Object();
	static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	static ChangeObjectThread t2 = new ChangeObjectThread("t2");
	
	public static class ChangeObjectThread extends Thread{
		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		
		public void run() {
			synchronized(u) {
				System.out.println("in "+ getName());
				LockSupport.park();
				if(Thread.interrupted()) {
					System.out.println(getName() + " 被中断了");
				}
			}
			System.out.println(getName() + " 执行结束");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		t1.start();
		Thread.sleep(1000);
		t2.start();
		t1.interrupt(); //t1在此处中断后，t1立即响应这个中断，并且使得 LockSupport.park() 返回null 让下面的代码继续执行
		LockSupport.unpark(t2);
		
		
		
	}

}
