package com.example.demo.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class IOtest01 {

	public static void main(String[] args) throws Exception {
		File file = new File("D:\\D-20171129-000001.txt");
		File newFile = new File("D:\\MyText.txt");
		if(file.isFile()){
			FileInputStream str = new FileInputStream(file);
			int word = 0;
			String strs = "";

			InputStream fio = new FileInputStream(file);
			fio = new BufferedInputStream(fio);
			
			
			int bytesRead = 0;
//			int bytesToRead = 1024;//只读1024次   可以用available来确定不阻塞的情况下有多少字节可以读取
			int bytesToRead = str.available();
			byte[] intput = new byte[bytesToRead];
			while(bytesRead < bytesToRead){
				int result = str.read(intput, 0, bytesToRead);
				if(result == -1){
					break;
				}
				bytesRead += result;
			}
			System.out.println(new String(intput));
			if(str.markSupported()){
				str.mark(20);
				str.reset();
				while(true){
					word = str.read();
					if(word == -1){
						break;
					}
					strs += word;
					System.out.println(word);
				}
				
				OutputStream out = new FileOutputStream(newFile);
				byte[] io = strs.getBytes();
				out.write(io);
				
				out.flush();
				out.close();
			}
			
			
			str.close();
		}
	}

	public static void generrateCharacters(OutputStream out) throws Exception{
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacter = 94;
		int numberOfCharactersPerLine = 72;
		
		int start = firstPrintableCharacter;
		while(true){
			for(int i = start;i<start+numberOfCharactersPerLine;i++){
				out.write(((i - firstPrintableCharacter) % numberOfPrintableCharacter)+firstPrintableCharacter);
			}
			out.write('\r');
			out.write('\n');
			start = ((start+1)-firstPrintableCharacter) % numberOfPrintableCharacter + firstPrintableCharacter;
		}
	}
}
