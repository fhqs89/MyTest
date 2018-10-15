package com.example.demo;


/**
 * 打印ABC失败
 * @author zh
 *
 */
public class PrintABC {

	public static void main(String[] args) {
		
		Print p1 = new Print(0);
		Print p2 = new Print(1);
		Print p3 = new Print(2);
		
		new Thread(p1,"p1").start();
		new Thread(p2,"p2").start();
		new Thread(p3,"p3").start();
		
		System.out.println("Done!");
	}
	
	
public static class Print implements Runnable{

	private int state = 0;
	private int id;
	private Object lock = new Object();
	
	public Print (int id){
		this.id = id;
	}
	
	
	@Override
	public void run() {
		synchronized(lock){
			while(state < 30){
				if(state % 3 == id){
					switch(id){
					case 0:
						System.out.println("A");
						break;
					case 1:
						System.out.println("B");
						break;
					case 2:
						System.out.println("C");
						break;
					default:
						break;
					}
				}else{
					try{
						lock.wait();
					}catch(Exception d){
						d.printStackTrace();
					}
				}
			}
		}
	}
	
}
	
}
