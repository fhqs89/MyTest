package com.example.demo.bio.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class BioSocket {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1",9000);
		System.out.println("请输入请求消息：");
		new ReadMsg(socket).start();
		PrintWriter pw = null;
		while(true) {
			pw = new PrintWriter(socket.getOutputStream());
			pw.println(new Scanner(System.in).next());
			pw.flush();
		}
		
	}
	
	public static class ReadMsg extends Thread{
		Socket socket;
		public ReadMsg(Socket socket) {
			this.socket = socket;
		}
		
		public void run() {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
				String line = null;
				while(true) {
					while((line = br.readLine()) != null){
						System.out.printf("%s\n",line);
					}
				}
			}catch(Exception r) {
				System.out.printf("%s\n","服务器断开了链接");
			}finally {
				if(socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}

}
