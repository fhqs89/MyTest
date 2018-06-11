package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.controller.util.DistributedLock;
import com.example.demo.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	
	
	@Override
	public void test() {
		DistributedLock lock = new DistributedLock("myCup");
		lock.lock();
		System.out.println("=============1");
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				DistributedLock locks = new DistributedLock("myCup");
				
				locks.lock();
				System.out.println("-------------");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				locks.unlock();
				
			}
			
		});
		t1.start();
		System.out.println("=============2");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		lock.unlock();
	}

}
