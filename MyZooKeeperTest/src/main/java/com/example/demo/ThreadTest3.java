package com.example.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 连续输出ABC失败
 * @author zh
 *
 */
public class ThreadTest3 {
	
	public static void main(String[] args) {
		
		PrintABC printABC = new PrintABC();
		for(int i=0;i<10;i++){
			new Thread(()->{
				printABC.printA();
			}).start();;
			
			new Thread(()->{
				printABC.printB();
			}).start();
			
			new Thread(()->{
				printABC.printC();
			}).start();
			printABC.setFlag(0);
		}
		
		
	}
	
public static class PrintABC {
	public PrintABC(){}
	
	public final Lock lock = new ReentrantLock();
	public Condition lockA = lock.newCondition();
	public Condition lockB = lock.newCondition();
	public Condition lockC = lock.newCondition();
	
	int flag = 0;

	public void setFlag(int flag){
		this.flag = flag;
	}
	public void printA(){
		lock.lock();
		try{
			while(true){
				while(flag != 0){
					lockA.await();
				}
				System.out.println("A");
				flag = 1;
				lockB.signal();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
	
	public void printB(){
		lock.lock();
		try{
			while(flag!=1){
				lockB.await();
			}
			System.out.println("B");
			flag = 2;
			lockC.signal();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void printC(){
		lock.lock();
		try{
			while(flag != 2){
				lockC.await();
			}
			System.out.println("C");
			flag = 3;
			lockA.signal();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
}
	
}
