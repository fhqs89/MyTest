package com.example.demo.thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrTest02 implements Runnable{

	public static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		
		ReentrTest02 t = new ReentrTest02();
		
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		
		t1.start();
		t2.start();
		
		
	}

	@Override
	public void run() {
		try {
//			lock.lock();  //此方便并不返回boolean，也意味着，其他线程必须等他释放了才能获得该锁
//			if(lock.isHeldByCurrentThread()) {
			if(lock.tryLock(2, TimeUnit.SECONDS)) {
				System.out.println(Thread.currentThread().getName() + " -get lock succed waiting ...");
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName() + " -get lock ok");
			}else {
				System.out.println(Thread.currentThread().getName() + " -get lock faild");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}

}
