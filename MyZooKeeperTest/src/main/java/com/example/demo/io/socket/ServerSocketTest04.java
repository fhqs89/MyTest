package com.example.demo.io.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.example.demo.io.socket.thread.DayTimeThread;

public class ServerSocketTest04{
	
	public static void main(String[] args) {
		for(int i =0 ;i<1024;i++) {
			try {
				ServerSocket ser = new ServerSocket(i);
				
			} catch (IOException e) {
				System.out.println("there is a server on port " + i);
			}
		}
	}



}
