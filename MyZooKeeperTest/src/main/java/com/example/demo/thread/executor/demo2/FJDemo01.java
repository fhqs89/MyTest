package com.example.demo.thread.executor.demo2;

import java.util.concurrent.RecursiveAction;

public class FJDemo01 extends RecursiveAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2184811387851792517L;
	public static int MAX = 20;
	private int start;
	private int end;
	
	public FJDemo01(int start,int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected void compute() {
		if((end - start) < MAX) {
			for(int i=start; i< end; i++) {
//				System.out.println(Thread.currentThread().getName() +"--" + i);
			}
		}else {
			//把任务分成两个任务，如果任务
			System.out.println(Thread.currentThread().getName() +"--" + "--拆分任务");
			int middle = (start + end) / 2;
			FJDemo01 left = new FJDemo01(start,middle);
			FJDemo01 right = new FJDemo01(middle,end);
			left.fork();
			right.fork();
		}
		
	}

}
