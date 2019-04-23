package com.example.demo.thread.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionJDKTest {

	
   final Lock lock = new ReentrantLock();
   final Condition notFull  = lock.newCondition(); 
   final Condition notEmpty = lock.newCondition(); 

   final Object[] items = new Object[9];
   int putptr, takeptr, count;

   public void put(Object x) throws InterruptedException {
     lock.lock(); //对代码块加锁
     try {
       while (count == items.length) //如果队列已满就等待
         notFull.await();
       items[putptr] = x; 
       if (++putptr == items.length) putptr = 0;
       ++count;
       notEmpty.signal(); //释放取线程锁
     } finally {
       lock.unlock();
     }
     System.out.println(Thread.currentThread().getName() + " --- "+ items[count-1]);
   }

   public Object take() throws InterruptedException {
     lock.lock();
     try {
       while (count == 0) 
         notEmpty.await();  //队列已空，取出线程阻塞
       Object x = items[takeptr]; 
       if (++takeptr == items.length) takeptr = 0;
       --count;
       notFull.signal(); //队列有足够空间，释放锁
       return x;
     } finally {
       lock.unlock();
     }
   } 
	
	
	public static void main(String[] args) throws InterruptedException {
		ConditionJDKTest test = new ConditionJDKTest();
		
		for(int i=0;i<11;i++) {
			int m = i;
			Thread t = new Thread(()->{
				try {
					test.put(m);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			t.start();
		}
		
		
		
//		test.put(1);
//		test.put(2);
//		test.put(3);
//		test.put(4);
//		test.put(5);
//		test.put(6);
		
		

	}

}
