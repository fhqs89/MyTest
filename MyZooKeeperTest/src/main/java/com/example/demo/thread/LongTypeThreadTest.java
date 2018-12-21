package com.example.demo.thread;

/**
 * 验证long在32位环境下的非原子性协议
 * 必须是32位系统
 * @author zhanghao105
 *
 */
public class LongTypeThreadTest {

	public static long t=0;
	public static class ChangeT extends Thread{
		
		private long to;
		public ChangeT(long to) {
			this.to = to;
		}
		public void run() {
			while(true) {
				LongTypeThreadTest.t = to;
				Thread.yield();
			}
		}
		
	}
	
	public static class ReadT implements Runnable{

		@Override
		public void run() {
			while(true) {
				long tmp = LongTypeThreadTest.t;
				if(tmp != 111l && tmp !=-999l && tmp != 333l && tmp != -444l) {
					System.out.println(tmp);
				}
				Thread.yield();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		new Thread(new ChangeT(111l)).start();
		new Thread(new ChangeT(-999l)).start();
		new Thread(new ChangeT(333l)).start();
		new Thread(new ChangeT(-444l)).start();
		new Thread(new ReadT()).start();

	}

}
