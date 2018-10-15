package com.example.demo;

import java.util.concurrent.Semaphore;

public class PrintABCSemaphore {
	
	private static Semaphore semaphoreA = new Semaphore(1);
	private static Semaphore semaphoreB = new Semaphore(0);
	private static Semaphore semaphoreC = new Semaphore(0);

	private static int count = 1;
	private static Thread threadA = new Thread(()->{
		while(true){
			try{
//				if(count >= 30){
//					System.out.println("A break ----" + count);
//					break;
//				}
				semaphoreA.acquire();
				System.out.println("A" + count);
				Thread.sleep(100);
				if(count < 30){
					semaphoreB.release();
				}else{
					break;
				}
				count++;
			}catch(Exception e){
				
			}
		}
	});
	
	private static Thread threadB = new Thread(()->{
		while(true){
			try{
//				if(count >= 30){
//					System.out.println("B break ----" + count);
//					break;
//				}
				semaphoreB.acquire();
				System.out.println("B" + count);
				Thread.sleep(100);
				if(count < 30){
					semaphoreC.release();
				}else{
					break;
				}
				count++;
			}catch(Exception e){
				
			}
		}
	});
	
	private static Thread threadC = new Thread(()->{
		while(true){
			try{
//				if(count >= 30){
//					System.out.println("C break ----" + count);
//					break;
//				}
				semaphoreC.acquire();
				System.out.println("C" + count);
				Thread.sleep(100);
				if(count < 30){
					semaphoreA.release();
				}else{
					break;
				}
				count++;
			}catch(Exception e){
				
			}
		}
	});
	
	public static void main(String[] args) throws InterruptedException {
		threadA.start();
		threadB.start();
		threadC.start();
		
		Thread.currentThread().join();

	}

}
