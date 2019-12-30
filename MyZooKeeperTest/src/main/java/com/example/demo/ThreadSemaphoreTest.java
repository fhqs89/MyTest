package com.example.demo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSemaphoreTest {

	private static Semaphore s1 = new Semaphore(1);
	static int i = 0;
	
	private static Lock lock = new ReentrantLock();
	private static CyclicBarrier cy = new CyclicBarrier(10);
	
	public static void main(String[] args) throws InterruptedException {
		for(int j=0;j<10;j++) {
			Thread t = new Thread(new ThreatTestss("t"+j));
			Thread t1 = new Thread(new ThreatTestss("t1"+j));
			Thread t2 = new Thread(new ThreatTestss("t2"+j));
			Thread t3 = new Thread(new ThreatTestss("t3"+j));
			t.start();
			t1.start();
			t2.start();
			t3.start();
		}
		
	}
	
	public static class ThreatTestss implements Runnable{
		public String msg;
		public ThreatTestss(String msg) {
			this.msg = msg;
		}
		@Override
		public void run() {
			try {
//				s1.acquire();
//				i++;
//				System.out.println("线程"+msg + " " + i);
//				s1.release();
				
//				lock.lock();
//				i++;
//				System.out.println("线程"+msg + " " + i);
//				lock.unlock();
				
				i++;
				System.out.println("线程"+msg + " " + i);
				cy.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
