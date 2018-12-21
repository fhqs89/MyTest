package com.example.demo.instrumentation.demo1;

import java.lang.instrument.Instrumentation;

public class PremainTest {
	
	
	public static void premain(String agentArgs,Instrumentation inst) {
		
		try {
			for(int i=0;i<5;i++) {
				System.out.println(" This is PremainTest.premain method i="+i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
		
		System.out.println("this is main method ...");
		

	}

}
