package com.example.demo.io.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketNIOTestSer02 {

	public static void main(String[] args) {
		try {
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			ServerSocket serverSocket = serverChannel.socket();
			serverSocket.bind(new InetSocketAddress(19));
			
			//jdk1.7后，可以直接通过 serverChannel绑定端口
			//serverChannel.bind(new InetSocketAddress(19));
			
			SocketChannel clientChannel = serverChannel.accept();
			clientChannel.configureBlocking(false);
			serverChannel.configureBlocking(false);
			
			
			Selector selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			SelectionKey key = clientChannel.register(selector, SelectionKey.OP_WRITE);
			
			
			
			
			
			
			serverChannel.accept();
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
