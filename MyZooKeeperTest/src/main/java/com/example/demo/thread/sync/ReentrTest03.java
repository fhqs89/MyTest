package com.example.demo.thread.sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 这种写法很容易造成死锁，但是由于用手了tryLock方法，运行足够长的时间，就会得到想要的结果。
 * @author zhanghao105
 *
 */
public class ReentrTest03 implements Runnable{

	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	
	int lock;
	
	public ReentrTest03(int lock) {
		this.lock = lock;
	}
	
	
	public static void main(String[] args) {
		
		ReentrTest03 r1 = new ReentrTest03(1);
		ReentrTest03 r2 = new ReentrTest03(2);
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		
		
		
	}

	@Override
	public void run() {
		if(lock == 1) {
			while(true) {
				if(lock1.tryLock()) {
					try {
						Thread.sleep(500);
						if(lock2.tryLock()) {
							try {
								System.out.println(Thread.currentThread().getName()+": my job id done");
								return;
							}finally {
								lock2.unlock();
							}
						}
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						lock1.unlock();
					}
				}
			}
		}else {
			while(true) {
				if(lock2.tryLock()) {
					try {
						Thread.sleep(500);
						if(lock1.tryLock()) {
							try {
								System.out.println(Thread.currentThread().getName()+": my job id done");
								return;
							}finally {
								lock1.unlock();
							}
						}
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						lock2.unlock();
					}
				}
				
			}
		}
	}

}
