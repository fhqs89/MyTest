package com.example.demo.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;

public class IOtest03_ReaderWriter {

	public static void main(String[] args) throws Exception{
		
		File file = new File("D:\\D-20171129-000001.txt");
		File newFile = new File("D:\\MyText.txt");
		
		InputStreamReader in = new InputStreamReader(new FileInputStream(file),"MacCyrillic");
		
		Reader read = new BufferedReader(in);
		StringBuffer sbr = new StringBuffer();
		int c;
//		while((c=in.read()) != -1){   //不使用缓冲阅读器BufferedReader
		while((c=read.read()) != -1){
			sbr.append((char)c);
		}
		System.out.println(sbr.toString());
		
		OutputStream out = new FileOutputStream(newFile);
		String msg = "12345abcde你好!@！@\\0133d\u0083";
		out.write(msg.getBytes());
		OutputStreamWriter wr = new OutputStreamWriter(out,"GBK");
		while(true){
			break;
			
		}
		System.out.println(wr.getEncoding());
//		wr.write("avbwef134r你好vadsv", 7, 4);
		wr.flush();
		
		wr.close();
		in.close();
		

	}

}
