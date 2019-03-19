package com.example.demo.thread.guavaDemo;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest01 {
	
	static RateLimiter limiter = RateLimiter.create(3);
	
	public static class Task implements Runnable{
		
		public void run() {
			System.out.println(System.currentTimeMillis());
		}
	}

	public static void main(String[] args) {
		for(int i=0;i < 50;i++) {
//			limiter.acquire();	//每秒最多 RateLimiter.create(3) 条
			
			if(!limiter.tryAcquire()) { //请求成功 返回true 所以基本上
				continue;
			}
			
			
			new Thread(new Task()).start();
		}

	}

	

}
