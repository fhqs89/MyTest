package com.example.demo.io.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.demo.io.socket.thread.DayTimeCallable;
import com.example.demo.io.socket.thread.DayTimeThread;
/**
 * 使用线程池的简单server
 * @author zh
 *
 */
public class ServerSocketTest03{
	
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newFixedThreadPool(50);
		
		try {
			ServerSocket ser = new ServerSocket(8089);
			while(true) {
				try{
					Socket connection = ser.accept();
					Callable<Void> task = new DayTimeCallable(connection);
					pool.submit(task);
				}catch(Exception e){
					System.out.println("0-------");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
