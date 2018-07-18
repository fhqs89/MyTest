package com.example.demo.io;

import java.io.ByteArrayInputStream;
import java.io.File;

/**
 *  字(Byte)节是长度单位。位（bit)也是长度单位。
 *  Byte与bit是不同的概念
 *  bit 是二进制的1位  0101 这儿的一个0和1占的地方就叫bit(位)
 *  1Byte = 8bit  1字节等于8位
 *  1KB=1024B
 * @author zh
 *
 */
public class IOtest02_ByteAndbit {

	public static void main(String[] args) {
		File file = new File("D:\\D-20171129-000001.txt");
		File newFile = new File("D:\\MyText.txt");
		
		int b = 5;
		
		byte bi = 5;
		
		if(b == bi){
			System.out.println("==");
		}
		
	}

}
