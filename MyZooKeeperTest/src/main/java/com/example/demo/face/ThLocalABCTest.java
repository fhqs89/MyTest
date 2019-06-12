package com.example.demo.face;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThLocalABCTest {

	static Lock ra = new ReentrantLock();
	static Condition ca = ra.newCondition();
	
	static ReentrantLock rb = new ReentrantLock();
	static Condition cb = rb.newCondition();
	
	static ReentrantLock rc = new ReentrantLock();
	static Condition cc = rc.newCondition();
	
	static ReentrantLock rd = new ReentrantLock();
	static Condition cd = rd.newCondition();
	
	public static class PrintA extends Thread{
		public void run() {
			try {
				rb.lock();
				rc.lock();
				rd.lock();
				System.out.print("A");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rb.unlock();
					rc.unlock();
					rd.unlock();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
	}
	
	public static class PrintB extends Thread{
		public void run() {
			try {
				rb.lock();
				rc.lock();
				rd.lock();
				System.out.print("B");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rb.unlock();
					rc.unlock();
					rd.unlock();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static class PrintC extends Thread{
		public void run() {
			try {
				rb.lock();
				rc.lock();
				rd.lock();
				System.out.print("C");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rb.unlock();
					rc.unlock();
					rd.unlock();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static class PrintD extends Thread{
		public void run() {
			try {
				rb.lock();
				rc.lock();
				rd.lock();
				System.out.print("D");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		Thread a = new PrintA();
		Thread b = new PrintB();
		Thread c = new PrintC();
		Thread d = new PrintD();
		c.start();
		b.start();
		d.start();
		a.start();
		
		
		
	}

}
