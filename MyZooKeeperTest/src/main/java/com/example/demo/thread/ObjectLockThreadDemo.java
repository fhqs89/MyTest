package com.example.demo.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 两个线程同时取票，但是同步代码块使用的是 synchorized(obj)，在执行时，会出现票号为 0 
 * 所以，两个线程出现了线程非安全，两个线程同时在跑，因此，
 * 此时的synchorized同步代码块 和 同步方法 不是同一个锁锁锁锁锁
 * 但是，当同步代码块变为 synchorized(this) 时，就不会出现上面情况，
 * 所以，synchorized方法 喝 synchorized(this) 一样会是对象锁
 *  synchorized方法
 * @author zh
 *
 */
public class ObjectLockThreadDemo {
	

	public static void main(String[] args) {
//		ExecutorService exe = Executors.newCachedThreadPool();
		
		CyclicBarrier cy = new CyclicBarrier(2);
		
		Ticket t = new Ticket();
		System.out.println("t   :"+ t);
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		t1.start();
		
		/*
		 *在主进程main中，t1.start和t2.start以及t.flag=false同时运行到了
		 *主线程进行了flag->false的过程
		 *t1和t2线程都只能在flag=false的同步函数中进行
		 */
		try {Thread.sleep(10);}catch(Exception e) {e.printStackTrace();};
		
		t.flag = false;
		t2.start();
		
//		exe.execute(t1);
//		exe.execute(t2);
		
	}

}

/**
 * 有100张票，一个是锁同步代码块，一个是锁方法
 * @author zh
 *
 */
class Ticket implements Runnable{
	
	CyclicBarrier cy = null;
	boolean flag = true;
	public Ticket(CyclicBarrier cy) {
		this.cy = cy;
	}
	public Ticket() {}

	private int num = 300;
	
	Object obj = new Object();
	
	@Override
	public void run() {
//		try {
//			cy.await();
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		} catch (BrokenBarrierException e1) {
//			e1.printStackTrace();
//		}
		
		System.out.println("this:"+ this);
		if(flag) {
			for(int i=200;i>1;i--) {
				synchronized(obj) {
					if(num > 0) {
						try {Thread.sleep(20);}catch(Exception e) {e.printStackTrace();};
						System.out.println(Thread.currentThread().getName()+"...obj..."+ num--);
					}
				}
			}
			
			
		}else {
			for(int i=200;i>1;i--) {
				show();
			}
		}
	}
	
	public synchronized void show() {
		if(num > 0) {
			try {Thread.sleep(20);}catch(Exception e) {e.printStackTrace();};
			System.out.println(Thread.currentThread().getName()+"...function.."+num--);
		}
	}
	
	
	
}
