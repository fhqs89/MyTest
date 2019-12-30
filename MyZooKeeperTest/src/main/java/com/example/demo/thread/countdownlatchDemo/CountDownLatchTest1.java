package com.example.demo.thread.countdownlatchDemo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 倒计数器
 * 当所有线程就为后，主线程方可执行。
 * @author zhanghao
 *
 */
public class CountDownLatchTest1 implements Runnable{
	
	static final CountDownLatch end = new CountDownLatch(10);
	static final CountDownLatchTest1 demo = new CountDownLatchTest1();

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++) {
			exec.submit(demo);
		}
		//等待检查
		end.await(); 
		//执行
		System.out.println("Fire!");
		exec.shutdown();
	}

	@Override
	public void run() {
		try {
			//模拟检查任务
			Thread.sleep(new Random().nextInt(10)*1000);
			System.out.println("check cmplete");
			end.countDown();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
