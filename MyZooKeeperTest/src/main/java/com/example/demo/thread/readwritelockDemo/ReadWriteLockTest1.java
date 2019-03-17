package com.example.demo.thread.readwritelockDemo;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁 
 * 读读之间不阻塞
 * 读写之间阻塞
 * 写写之间也阻塞
 * @author zhanghao
 *
 */
public class ReadWriteLockTest1 {

	private static Lock lock = new ReentrantLock();
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private static Lock readLock = readWriteLock.readLock();
	private static Lock writeLock = readWriteLock.writeLock();
	private int value;

	public Object handleRead(Lock lock) throws InterruptedException{
		try {
			lock.lock();  //模拟读操作
			Thread.sleep(1000); //读操作的耗时越多，读写锁的优势就越明显
			System.out.println(Thread.currentThread().getName() + " read" + value);
			return value;
		}finally {
			lock.unlock();
		}
	}
	
	public void handleWrite(Lock lock,int index) throws InterruptedException{
		try {
			lock.lock(); //模拟写操作
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " write" + index);
			value = index;
		}finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		
		final ReadWriteLockTest1 demo = new ReadWriteLockTest1();
		
		Runnable r = new Runnable(){
			@Override
			public void run() {
				try {
					demo.handleRead(readLock);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		Runnable w = new Runnable(){
			@Override
			public void run() {
				try {
					demo.handleWrite(writeLock, new Random().nextInt());
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};
		
		for(int i=0; i< 18; i++) {
			new Thread(r).start();
		}
		
		for(int i=18; i< 20 ;i++) {
			new Thread(w).start();
		}
	}

}
