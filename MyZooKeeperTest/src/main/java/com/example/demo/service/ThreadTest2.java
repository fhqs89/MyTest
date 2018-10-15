package com.example.demo.service;

public class ThreadTest2 {

	public static void main(String[] args) {
		 
		
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.print("a");
			}
			
		});
		
		Thread t2 = new Thread(()->{
			System.out.print("b");
		});
		
		for(int i=0;i<10;i++){
			
			t1.run();
			t2.run();
			
			System.out.print("c");
			
			
		}
		
		
		
		
	}

}
