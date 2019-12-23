package com.example.demo.bio.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.example.demo.bio.Ch01Const;

public class BioServerHandler implements Runnable{
	
	private Socket socket;
	public BioServerHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(socket.getInputStream()))){
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			String message;
			String result;
			while((message = in.readLine()) != null) {
				System.out.println("server accept message !");
				result = Ch01Const.response(message);
				out.println(result);
			}
			
			
		}catch(Exception e) {
			
		}finally {
			if(socket != null) {
				try {
					socket.close();
				}catch(Exception e ) {
					e.printStackTrace();
				}
				socket = null;
			}
		}
		
	}
	
	

}
