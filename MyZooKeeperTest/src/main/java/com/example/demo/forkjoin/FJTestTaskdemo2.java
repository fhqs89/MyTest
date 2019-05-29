package com.example.demo.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 计算从start 到 end 的整数相加的和
 * 因为是要有返回值，所以可以不用invoke，future.get()一样会阻塞。
 * @author zhanghao105
 *
 */
public class FJTestTaskdemo2 extends RecursiveTask<Integer>{

	private static final long serialVersionUID = 2668801485268711937L;
	private Integer start;
	private Integer end;
	
	static int MAX = 5;
	
	public FJTestTaskdemo2(int start,int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Integer compute() {
		//如果满足运算条件（ 每个任务相加的数量小于20个） 就进行累加
		if((end - start)< MAX) {
			int total = 0;
			for(int i=start;i<=end;i++ ) {
				total +=i;
			}
			System.out.println(Thread.currentThread().getName() + " 计算: start=" + start + "  end="+end + " ##" + total);
			return total;
		}else {
			Integer moddle = (start + end)/2;
			FJTestTaskdemo2 taskL = new FJTestTaskdemo2(start, moddle);
			taskL.fork();
			FJTestTaskdemo2 taskR = new FJTestTaskdemo2(moddle + 1, end);
			taskR.fork();
			return taskL.join() + taskR.join();
		}
	}

	public static void main(String[] args) throws Exception {
		ForkJoinPool pool = new ForkJoinPool(3);
		FJTestTaskdemo2 task = new FJTestTaskdemo2(1,20);
		
		ForkJoinTask<Integer> taskFuture = pool.submit(task);
		Integer result2 = taskFuture.get();
		
		Integer result = pool.invoke(task);
		System.out.println("result = " + result);
		
	}
}
