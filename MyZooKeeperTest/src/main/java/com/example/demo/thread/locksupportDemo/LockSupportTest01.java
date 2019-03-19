package com.example.demo.thread.locksupportDemo;

import java.util.concurrent.locks.LockSupport;

/**
 * 对比类InterrputTest2 的方法
 * @author zhanghao
 *
 */
public class LockSupportTest01 {

	public static Object u = new Object();
	static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	static ChangeObjectThread t2 = new ChangeObjectThread("t2");
	
	public static class ChangeObjectThread extends Thread{
		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		
		public void run() {
			synchronized (u) {
				System.out.println("in " + getName());
				LockSupport.park(this);
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		LockSupport.unpark(t2);
//		LockSupport.unpark(t1); //无论先park 还是 先unpark 只要有调用过这个方法 就可以释放线程 原理是类似信号量都机制
		t1.join();
		t2.join();
		
	}

}
