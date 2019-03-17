package com.example.demo.thread.SemapDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest1 implements Runnable{

	final Semaphore semp = new Semaphore(5);
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(20);
		final SemaphoreTest1 demo = new SemaphoreTest1();
		for(int i=0;i<20;i++) {
			exec.submit(demo);
		}
		
	}

	@Override
	public void run() {
		try {
			semp.acquire();
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + " : done");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semp.release();
		}
	}
	

}
