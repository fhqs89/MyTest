package com.example.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABCLockTest {
	
	private static Lock lock = new ReentrantLock();
	private static Condition conditionA = lock.newCondition();
	private static Condition conditionB = lock.newCondition();
	private static Condition conditionC = lock.newCondition();
	
	private static int count =1;
	
	private static Thread threadA = new Thread(()->{
		while(true){
			try{
				lock.lock();
				
				while(count% 3!= 1){
					conditionA.wait();
				}
				System.out.println(Thread.currentThread().getName()+"--A");
				Thread.sleep(100);
				count++;
				conditionB.signal();
				if(count == 30){
					break;
				}
			}catch(Exception e){
				
			}finally{
				lock.unlock();
			}
		}
	});
	
	private static Thread threadB = new Thread(()->{
		while(true){
			try{
				lock.lock();
				
				while(count% 3!= 2){
					conditionA.wait();
				}
				System.out.println(Thread.currentThread().getName()+"--B");
				Thread.sleep(100);
				count++;
				conditionC.signal();
				if(count == 30){
					break;
				}
			}catch(Exception e){
				
			}finally{
				lock.unlock();
			}
		}
	});
	
	private static Thread threadC = new Thread(()->{
		while(true){
			try{
				lock.lock();
				
				while(count% 3!= 0){
					conditionA.wait();
				}
				System.out.println(Thread.currentThread().getName()+"--C");
				Thread.sleep(100);
				count++;
				conditionA.signal();
				if(count == 30){
					break;
				}
			}catch(Exception e){
				
			}finally{
				lock.unlock();
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
