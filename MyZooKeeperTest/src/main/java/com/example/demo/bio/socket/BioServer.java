package com.example.demo.bio.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {

	private static  ServerSocket server;
	public static ExecutorService pool = Executors.newFixedThreadPool(3);

	public static void start() throws IOException {
		try {
			server = new ServerSocket(9000);
			System.out.println("服务器已启动 端口：9000");
			while(true) {
				Socket socket = server.accept();
				System.out.println("有新客户端请求");
				pool.execute(new BioServerHandler(socket));
			}
		}finally {
			if(server != null) {
				server.close();
			}
		}
			
	}
	
	public static void main(String[] args) throws IOException {
			
		start();

	}

}
