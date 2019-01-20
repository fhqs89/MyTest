package com.example.demo.thread.demo1;

public class WaitTest {
	 static Object obj = new Object();
	
	
	
	public static class Thread1 extends Thread{
		@Override
		public void run() {
			synchronized(obj) {
				System.out.println(System.currentTimeMillis()+":T1 start!");
				try {
					System.out.println(System.currentTimeMillis() + ":T1 wait for objevt");
					obj.wait();
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis()+":T1 end!");
				
			}
		}
	}
	
	public static class Thread2 extends Thread{
		@Override
		public void run() {
			synchronized(obj) {
				System.out.println(System.currentTimeMillis()+":T2 start ! notify one thread");
				obj.notify();
				System.out.println(System.currentTimeMillis()+ ":T2 end!");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
		
		t1.start();
		t2.start();
		
	}

}
