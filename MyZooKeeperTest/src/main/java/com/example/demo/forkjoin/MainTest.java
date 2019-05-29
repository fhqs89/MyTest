package com.example.demo.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainTest {

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		FJTestTaskdemo1 de = new FJTestTaskdemo1(1,20);
		forkJoinPool.invoke(de);
		try {
			de.get(180, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

}
