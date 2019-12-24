package com.example.demo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadTest4 {
	
	public static ScheduledExecutorService ex = Executors.newScheduledThreadPool(5);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		try {
			Callable<Integer> t1 = new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					System.out.println("线程 t1 执行");
					int num = new Random().nextInt(100);//生成随机数
//                    System.out.println(Thread.currentThread().getName() + " : " + num);
					return num;
				}
			};
			for(int i=0;i<5;i++) {
				Future<Integer> fu = ex.schedule(t1, 2, TimeUnit.SECONDS);
				System.out.println(fu.get() + " " + i);
			}
			
		}finally {
			ex.shutdown();
		}
		
	}

}
