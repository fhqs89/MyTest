package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * hashMap 
 * @author zh
 *
 */
public class Test {
	//threshold（扩容阈值）和loadFactor 加载因子
	public final static int MAXIMUM_CAPACITY_a = 1073741824 ;
	static final int MAXIMUM_CAPACITY = 1 << 30;

	public static void main(String[] args) {
		//hashMap 设置数组的长度，同时指定  扩容长度为 2* 0.75  同时会扩大一倍，并且是容量是2的倍数
		Map<String,String> map = new HashMap<String,String>(2);
		
		Map<String,String> maps = new HashMap<String,String>();
		map.put("aa", "bb");
		map.put("aaa", "bbb");
		map.put("aaaa", "bbbb");
		System.out.println(map.get("aa"));
		
		int cap = 2;
	        int n = cap - 1;
	        n |= n >>> 1; System.out.println(n);
	        n |= n >>> 2; System.out.println(n);
	        n |= n >>> 4; System.out.println(n);
	        n |= n >>> 8; System.out.println(n);
	        n |= n >>> 16; System.out.println(n);
	        System.out.println( (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);
	    }
		

}
