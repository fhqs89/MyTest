package com.example.demo.thread.threadLocal;

public class ThreadLocalUseCase {
	
	public ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
	
	public ThreadLocal<Integer> getTl() {
		return tl;
	}

	public void setTl(ThreadLocal<Integer> tl) {
		this.tl = tl;
	}

}
