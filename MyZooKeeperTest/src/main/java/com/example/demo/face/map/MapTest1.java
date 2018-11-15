package com.example.demo.face.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest1 {

	public static void main(String[] args) {
		
		Map<String,String> map = new HashMap<String,String>();
		
		map.put(null, "aaa");
		map.put("adfadf", null);
		map.put(null, null);
		
		MySimpleAbstractMapImpl<String,String> mySimpleAbstractMap = new MySimpleAbstractMapImpl<String,String>();
//		mySimpleAbstractMap
		mySimpleAbstractMap.put("efwe", "ewfwef");
		mySimpleAbstractMap.get("");
		
		Map<String ,String> curMap = new ConcurrentHashMap<String,String>();
		
	}

}
