package com.example.demo.io.thread;

public class IOThreadCallback implements Runnable{

	private String fileName;
	
	private IOThreadMain iOThreadMain;
	
	public IOThreadCallback(String fileName){
		this.fileName = fileName;
	}
	
	public IOThreadCallback(String fileName, IOThreadMain iOThreadMain){
		this.fileName = fileName;
		this.iOThreadMain = iOThreadMain;
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(fileName);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(iOThreadMain != null){
			iOThreadMain.receiveDigest(fileName);
		}else{
			IOThreadMain.receiveStaticDigest(fileName);
		}
	}
	
}
