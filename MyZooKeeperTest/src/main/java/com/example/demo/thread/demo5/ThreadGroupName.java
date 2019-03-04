package com.example.demo.thread.demo5;

/**
 * 线程组
 * @author zhanghao
 *
 */
public class ThreadGroupName implements Runnable{

	public static void main(String[] args) {
		ThreadGroup tg = new ThreadGroup("PrintGroup");
		Thread t1 = new Thread(tg,new ThreadGroupName(),"T1");
		Thread t2 = new Thread(tg,new ThreadGroupName(),"T2");
		Thread t3 = new Thread(tg,new ThreadGroupName(),"T=3");
		Thread t4 = new Thread(tg,new ThreadGroupName(),"T=4");
		
		t1.start();
		t2.start();
		t3.start();
		System.out.println(tg.activeCount()+ "  hhh");
		
		tg.list();
		System.out.println("====");
		tg.list();
	}

	@Override
	public void run() {
		String groupAndName = Thread.currentThread().getThreadGroup().getName() + 
				"-" + Thread.currentThread().getName();
//		while(true) {
//			System.out.println("I am "+ groupAndName);
//			try{
//				Thread.sleep(3000);
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			
//		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
