package com.example.demo.thread.executor.demo1;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainTest {

	public static void main(String[] args) {
//		ThreadPoolExecutor pools = new ThreadPoolExecutor(0, 
//				Integer.MAX_VALUE,0L,TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
		
		ThreadPoolExecutor pools = new TraceThreadPoolExecutor(0, 
				Integer.MAX_VALUE,0L,TimeUnit.SECONDS,new SynchronousQueue<Runnable>());

		
		
		for(int i=0; i<5;i++) {
			pools.execute(new DivTask(100,i));  
		}
		
	}

}
