package com.example.demo.thread.sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁，会遵循既有的调度依次执行
 * @author zhanghao105
 *
 */
public class ReentrTest04 implements Runnable{
	
	public static ReentrantLock lock = new ReentrantLock(true);

	public static void main(String[] args) {
		
		ReentrTest04 r = new ReentrTest04();
		Thread t1 = new Thread(r,"Thread-1");
		Thread t2 = new Thread(r,"Thread-2");
		Thread t3 = new Thread(r,"Thread-3");
		t1.start();
		t2.start();
		t3.start();

	}

	@Override
	public void run() {
		while(true) {
			try {
				lock.lock();
				System.out.println(Thread.currentThread().getName()+ " get lock");
				Thread.sleep(500);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
		
	}

}
