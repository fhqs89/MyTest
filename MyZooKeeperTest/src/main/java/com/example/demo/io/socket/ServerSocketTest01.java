package com.example.demo.io.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
/**
 * 最简单的server
 * @author zh
 *
 */
public class ServerSocketTest01 {

	public static void main(String[] args) {
		try {
			ServerSocket ser = new ServerSocket(8089);
			while(true) {
				try(Socket connection = ser.accept()){
					OutputStream out = connection.getOutputStream();
					Writer wr = new OutputStreamWriter(out);
					//使用一个回车/换行对来结束这一行
					wr.write((new Date()).toString() + "哈哈  ABC" + "\r\n");
					wr.flush();
					wr.close();
					out.close();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
