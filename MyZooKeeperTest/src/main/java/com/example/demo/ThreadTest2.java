package com.example.demo;

public class ThreadTest2 extends Thread{

	private String name;
	private Object pre;
	private Object self;
	
	public ThreadTest2(String name, Object pre, Object self){
		this.name = name;
		this.pre = pre;
		this.self = self;
	}
	
	@Override
	public void run(){
		for(int i=0;i<10;i++){
			synchronized(pre){
				synchronized(self){
					
					System.out.println(name);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					self.notifyAll();
					
					try{
						pre.wait();
						System.out.println("wait" + name);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		
		
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		
		new ThreadTest2("A",c,a).start();
		Thread.sleep(500);
		new ThreadTest2("B",a,b).start();
		Thread.sleep(500);
		new ThreadTest2("C",b,c).start();
		Thread.sleep(500);
		
		
	}

}
