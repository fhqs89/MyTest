package com.example.demo.netty.buffer;

import java.nio.ByteBuffer;

public class BufferMethod {
	/**
	 * buffer 中 主要有3个描述
	 * position 下一个可写的位置，初始为0
	 * limit 表示能够写入的最末尾的位置
	 * capacity 大小容量，从0开始
	 * 
	 * 
	 * @param args
	 */
	

	public static void main(String[] args) {
		
		//堆上内存
//		System.out.println("before buffer " + Runtime.getRuntime().freeMemory());
//		ByteBuffer buffer = ByteBuffer.allocate(1024000);
//		System.out.println("after buffer " + Runtime.getRuntime().freeMemory());
		//直接内存分配
//		System.out.println("after buffer " + Runtime.getRuntime().freeMemory());
//		ByteBuffer directbuffer = ByteBuffer.allocateDirect(1024000);
//		System.out.println("after buffer " + Runtime.getRuntime().freeMemory());
		
		
		ByteBuffer buffer = ByteBuffer.allocate(32);//堆上申请buffer
		System.out.println("初始 buffer " + buffer);
		
		buffer.put((byte)'a');//0
		buffer.put((byte)'b');//1
		buffer.put((byte)'c');//2
		buffer.put((byte)'d');//3
		buffer.put((byte)'e');//4
		buffer.put((byte)'f');//5
		System.out.println("put 'a' buffer " + buffer);
		
		buffer.flip();
		
		System.out.println((char)buffer.get());//相对取，导致索引发生变化 a
		System.out.println("buffer " + buffer);//[pos=1 lim=6 cap=32]
		System.out.println((char)buffer.get(3));//相对取 d
		System.out.println("buffer " + buffer);//[pos=1 lim=6 cap=32]
		
		byte[] dat = new byte[10];
		buffer.get(dat, 0, 3);// 0为position的位置
		System.out.println("dat  " + new String(dat));//bcd
		System.out.println("buffer " + buffer); //[pos=4 lim=6 cap=32]
		
		
		
	}

}
