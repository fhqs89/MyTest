package com.example.demo.thread.executor.demo2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;


public class FJMain {

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(6);
		FJDemo01 f1 = new FJDemo01(0,2000);
		forkJoinPool.submit(f1);
		
		try {
			forkJoinPool.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
