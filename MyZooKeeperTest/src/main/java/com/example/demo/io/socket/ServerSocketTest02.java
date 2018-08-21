package com.example.demo.io.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.example.demo.io.socket.thread.DayTimeThread;
/**
 * 支持多连接的简单server
 * @author zh
 *
 */
public class ServerSocketTest02{
	
	public static void main(String[] args) {
		try {
			ServerSocket ser = new ServerSocket(8089);
			while(true) {
				try(Socket connection = ser.accept()){
					DayTimeThread thr = new DayTimeThread(connection);
					thr.run();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
