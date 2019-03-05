package com.example.demo.thread.sync;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrTest implements Runnable{

	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	
	int lock;
	
	/**
	 * 控制加锁顺序，方便构造死锁
	 * @param lock
	 */
	public ReentrTest(int lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		try{
			if(lock == 1) {
				lock1.lockInterruptibly();
				try {
					Thread.sleep(500);
				}catch(InterruptedException e) {
					lock2.lockInterruptibly();
				}
			}else {
				lock2.lockInterruptibly();
				try {
					Thread.sleep(500);
				}catch(InterruptedException e) {
					lock1.lockInterruptibly();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(lock1.isHeldByCurrentThread()) {//查询当前线程是否保持此锁定
				lock1.unlock();
			}
			if(lock2.isHeldByCurrentThread()) {
				lock2.unlock();
			}
			System.out.println(Thread.currentThread().getId()+":线程退出");
		}
		
	}

	public static void main(String[] args) throws Exception {
		ReentrTest r1 = new ReentrTest(1);
		ReentrTest r2 = new ReentrTest(2);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		Thread.sleep(1000);
		//中断其中一个线程
		t2.interrupt();
		System.out.println(Thread.currentThread().getId()+":线程退出");
	}
}
