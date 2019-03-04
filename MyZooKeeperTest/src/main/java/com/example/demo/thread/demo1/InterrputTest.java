package com.example.demo.thread.demo1;

public class InterrputTest{

	public static void main(String[] args) throws Exception {
		
		Thread t1 = new Thread() {
			public void run() {
				while(true) {
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("interrupt t1");
						break;
					}
					Thread.yield();//暂停当前正在执行的线程对象，并执行其他线程
				}
			}
		};

		t1.start();
		Thread.sleep(2000);
		t1.interrupt();
	}

}
