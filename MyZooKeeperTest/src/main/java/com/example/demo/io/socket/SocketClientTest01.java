package com.example.demo.io.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketClientTest01 {

	public static void main(String[] args) {
		try (Socket socket = new Socket("127.0.0.1",8089)){
			socket.setSoTimeout(15000);
			
			InputStream in = socket.getInputStream();
			Reader read = new InputStreamReader(in);
			int r = 0;
			while((r=read.read()) != -1){
				System.out.print((char)r);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}
