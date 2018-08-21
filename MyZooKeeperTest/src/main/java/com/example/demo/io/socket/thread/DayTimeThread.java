package com.example.demo.io.socket.thread;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Date;

public class DayTimeThread implements Runnable{

	private Socket connection;
	
	public DayTimeThread(Socket socket) {
		this.connection = socket;
	}
	
	@Override
	public void run() {
		Writer wr;
		try {
			wr = new OutputStreamWriter(connection.getOutputStream());
			//使用一个回车/换行对来结束这一行
			wr.write((new Date()).toString() + "哈哈  ABC" + "\r\n");
			wr.flush();
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
