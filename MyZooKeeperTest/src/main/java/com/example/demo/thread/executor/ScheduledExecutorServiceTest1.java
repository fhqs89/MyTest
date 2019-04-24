package com.example.demo.thread.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest1 {

	
	
	public static void main(String[] args) {
		
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
		
//		ses.scheduleAtFixedRate(command, initialDelay, period, unit)
		
		ses.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
					System.out.println(System.currentTimeMillis());
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}, 0, 2, TimeUnit.SECONDS);
		

	}

}
