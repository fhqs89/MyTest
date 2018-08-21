package com.example.demo.io.socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

/**
 * socket是长连接，读取流的时候一定要知道什么时候结束，
 * 否则会一直在循环读取流信息
 * @author zh
 *
 */
public class SocketTest01 {

	public static void main(String[] args) {
		try (Socket socket = new Socket("dict.org",2628)){
			socket.setSoTimeout(15000);
			
			OutputStream out = socket.getOutputStream();
			Writer writer = new OutputStreamWriter(out,"UTF-8");
			writer.write("DEFINE eng-lat gold\r\n");
			writer.flush();
			
			//一定要定义一个结束符号，不然不会跳出循环
//			InputStream in = socket.getInputStream();
//			Reader read = new InputStreamReader(in);
//			int r = 0;
//			while((r=read.read()) != -1){
//				System.out.print((char)r);
//			}
			
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			for(String line = reader.readLine();!line.equals(".");line = reader.readLine()){
				System.out.println(line);
				if(line.startsWith("550")){  //一定要定义一个结束符号，不然不会跳出循环
					break;
				}
			}
//			
			writer.write("q\r\n");
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
