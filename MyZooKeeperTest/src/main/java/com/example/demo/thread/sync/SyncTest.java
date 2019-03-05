package com.example.demo.thread.sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 * @author zhanghao
 *
 */
public class SyncTest implements Runnable{

	public static ReentrantLock lock = new ReentrantLock();
	
	public static int i=0;
	
	public static void main(String[] args) throws InterruptedException {
		
		SyncTest l1 = new SyncTest();
		Thread t1 = new Thread(l1);
		t1.setName("t1");
		Thread t2 = new Thread(l1);
		t2.setName("t2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}

	@Override
	public void run() {
		for(int j=0;j<1000000;j++) {
			lock.lock();
			try {
				i++;
				if(i%100==0) {
					System.out.println(Thread.currentThread().getName()+"---"+i);
				}
			}finally{
				lock.unlock();
			}
		}
		
	}

}
