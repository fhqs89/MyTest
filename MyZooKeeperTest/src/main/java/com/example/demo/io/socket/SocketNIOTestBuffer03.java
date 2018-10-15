package com.example.demo.io.socket;

import java.nio.IntBuffer;
import java.util.function.Function;

public class SocketNIOTestBuffer03 {

	public static void main(String[] args) {
		IntBuffer buf = IntBuffer.allocate(5);
		
		buf.put(0);
		buf.put(1);
		buf.put(2);
		buf.put(3);
		buf.put(4);
		
		buf.flip();
		int[] arr = buf.array();
		for(int i=0;i<arr.length;i++) {
			System.out.println(buf.get());
			System.out.println(arr[i]);
		}
		
		String strs = setHander("nihao  hhahh fe",(str) -> str.substring(0, 8));
		System.out.println(strs);
		
		
		
	}
	
	//函数式接口
	public static String setHander(String str,Function<String,String> fun) {
		return fun.apply(str);
	}
	

}
