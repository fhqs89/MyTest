package com.example.demo.thread.demo2;

public class InterrputTest2 {
	
	public static Object obj = new Object();
	
	
	public static class ChangeObjectThread extends Thread{
		
		public ChangeObjectThread(String msg) {
			super.setName(msg);
		}
		
		public void run() {
			synchronized(obj) {
				System.out.println("in  "+ this.getName());
				Thread.currentThread().suspend();
			}
		}
	}
	
	static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	static ChangeObjectThread t2 = new ChangeObjectThread("t2");
	

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception{
		t1.start();
		Thread.sleep(2000); //如果不增加这个等待 可能t2将无法得到 obj锁 进入零界区 因为等不到t1.resume()
		t2.start();
		t1.resume();//必须t1 resume后 t2才能拿到 obj锁
		t2.resume();//由于 主线程先执行了resume方法 导致子线程一直在suspend中
		t1.join();
		t2.join();
		

	}

}
