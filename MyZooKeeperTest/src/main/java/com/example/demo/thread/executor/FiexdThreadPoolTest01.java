package com.example.demo.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定大小的线程池
 * @author zhanghao
 *
 */
public class FiexdThreadPoolTest01 {

	public static class MyTask implements Runnable{

		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + " ID:" + Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		MyTask task = new MyTask();
		ExecutorService es = Executors.newFixedThreadPool(5);
		for(int i=0;i<10; i++) {
			es.submit(task);
		}
		
	}

}
