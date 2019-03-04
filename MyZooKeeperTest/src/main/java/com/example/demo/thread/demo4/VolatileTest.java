package com.example.demo.thread.demo4;

public class VolatileTest {

	
	public volatile static int i=0;
	
	public static class PlusTack implements Runnable{

		@Override
		public void run() {
			for(int k=0;k<10000;k++) {
				i++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Thread[] threads = new Thread[10];
		for(int j=0;j<10;j++) {
			threads[j] = new Thread(new PlusTack());
			threads[j].start();
		}
		for(int j=0;j<10;j++) {
			threads[j].join();
		}
		
		System.out.print(i);
	}

}
