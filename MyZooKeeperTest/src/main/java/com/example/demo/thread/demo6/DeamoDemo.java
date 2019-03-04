package com.example.demo.thread.demo6;

/**
 * 守护线程
 * @author zhanghao
 *
 */
public class DeamoDemo {

	public static class DaemoT extends Thread{
		public void run() {
			while(true) {
				System.out.println("I am alive");
				try {
					Thread.sleep(2000);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 如果不把线程设置为守护线程，线程将一直运行下去
	 * 线程被设置为守护线程，系统中只有主线程main为用户线程，因此在main方法休眠6秒后，整个程序也随之结束。
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Thread t = new DaemoT();
		t.setDaemon(true);
		t.start();
		
		Thread.sleep(6000);
	}

}
