package com.example.demo.interview;

import java.util.concurrent.Semaphore;

/**
 * 信号量 semA,semB,semC
 * 线程tA,tB,tC
 * 实现了3个线程轮流输出ABC10次
 * @author zh
 *
 */
public class ThreadSemaphoreABC1 {

	private static Semaphore semA = new Semaphore(1);
	private static Semaphore semB = new Semaphore(0);
	private static Semaphore semC = new Semaphore(0);
	
	private static Semaphore semD = new Semaphore(2);
	
	private static Thread tA = new Thread(()->{
		try {
			for(int i=0;i<9;i++) {
				semA.acquire();
				System.out.println("A");
				semB.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	});
	
	private static Thread tB = new Thread(()->{
		try {
			for(int i=0;i<10;i++) {
				semB.acquire();
				System.out.println("B");
				semC.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	});
	
	private static Thread tC = new Thread(()->{
		try {
			for(int i=0;i<10;i++) {
				semC.acquire();
				System.out.println("C");
				semA.release();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	});
	
	public static void main(String[] args) throws InterruptedException {
//		tA.start();
//		tB.start();
//		tC.start();
//		Thread.currentThread().join();  //主线程等待其他线程结束再执行
		
		
		t.start();
		t2.start();
//		semB.acquire();
		
		Thread.sleep(1000);
		System.out.println("TTTT");
		
	
	}

	/**
	 * Semaphore sem = Semaphore(0);   信号量为0时，只要 sem.release()线程就会执行，不论有没有sem.acquire()，
	 * 不过只要信号量获取执行许可，即sem.acquire()就会一直阻塞，直到执行sem.release()释放许可。 
	 */
	public static Thread t = new Thread(()->{
		
		try {
			semB.acquire();
			System.out.println("GGGG");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	});
	
	public static Thread t2 = new Thread(()->{
		
		try {
			Thread.sleep(3000);
			System.out.println("release");
			semB.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	});
	
}
