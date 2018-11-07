package com.example.demo.face;

import java.util.concurrent.Semaphore;

public class PrintABCDSemphoneTest {

	public static Semaphore semaphoreA = new Semaphore(1);
	public static Semaphore semaphoreB = new Semaphore(0);
	public static Semaphore semaphoreC = new Semaphore(0);
	public static Semaphore semaphoreD = new Semaphore(0);
	
	public static Thread t1 = new Thread(()->{
		while(true) {
			try {
				semaphoreA.acquire();
				System.out.println("A");
				semaphoreB.release();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	});
	
	public static Thread t2 = new Thread(()->{
		while(true) {
			try {
				semaphoreB.acquire();
				System.out.println("B");
				semaphoreC.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		});
	
	public static Thread t3 = new Thread(()->{
		while(true) {
			try {
				semaphoreC.acquire();
				System.out.println("C");
				semaphoreD.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	});

	public static Thread t4 = new Thread(()->{
		while(true){
			try {
				semaphoreD.acquire();
				System.out.println("D");
				semaphoreA.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		Thread.currentThread().join();

	}

}
