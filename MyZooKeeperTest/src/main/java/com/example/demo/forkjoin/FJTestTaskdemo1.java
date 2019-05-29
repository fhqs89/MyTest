package com.example.demo.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * ForkJoinPool 有三个提交执行线程的方法 execute submit 和 invoke 
 * 且他们内部都是 externalPush(task)方法
 * 区别是 invoke方法在返回时，有一个task.join()
 * 会使得主线程会等待 task 执行完毕再继续执行
 * @author zhanghao105
 *
 */
public class FJTestTaskdemo1 extends RecursiveAction{

	int MAX = 5;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int start = 0;
	public int end = 0;
	public FJTestTaskdemo1(int start,int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		
		if((end - start) < MAX) {
			for(int i=start;i<end;i++) {
				System.out.println(Thread.currentThread().getName() +" ---- " + i + "  " + end);
			}
		}else {
			int modile = (end + start) /2;
			FJTestTaskdemo1 left = new FJTestTaskdemo1(start, modile);
			FJTestTaskdemo1 right = new FJTestTaskdemo1(modile, end);
			left.fork();
			right.fork();
		}
	}
	
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(3);
		FJTestTaskdemo1 de = new FJTestTaskdemo1(1,20);
		forkJoinPool.invoke(de);
		try {
			de.get(3, TimeUnit.SECONDS);
//			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

}
