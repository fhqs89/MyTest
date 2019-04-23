package com.example.demo.thread.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程操作的wait()、notify()、notifyAll()方法只能在同步控制方法或同步控制块内调用。
 * 如果在非同步控制方法或控制块里调用，程序能通过编译，但运行的时候，将得到 IllegalMonitorStateException 异常。
 * Condition.await()与Condition.signal()方法调用时，都要求线程先要获得相关的锁。
 * @author zhanghao105
 *
 */
public class ConditionTest implements Runnable {
	
	public static ReentrantLock lock =  new ReentrantLock();
	public static Condition condition = lock.newCondition();

	public static void main(String[] args) throws Exception{
		ConditionTest test = new ConditionTest();
		
		Thread t1 = new Thread(test);
		t1.start();
		Thread.sleep(3000);

		//通知线程继续执行
		lock.lock();
		condition.signal(); //必须在同步代码块里，所以加lock和unlock
		lock.unlock(); //释放了该锁，子线程才能得到锁继续执行
	}

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + "thread come ");
			lock.lock();
			condition.await(); //必须在同步代码块里，所以加lock和unlock
			System.out.println(Thread.currentThread().getName() + "thread is going on ");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}

}
