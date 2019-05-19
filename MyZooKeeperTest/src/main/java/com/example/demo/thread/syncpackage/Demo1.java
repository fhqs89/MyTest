package com.example.demo.thread.syncpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Demo1 {

	public static void main(String[] args) {
		Map m = Collections.synchronizedMap(new HashMap());
		
		List l = Collections.synchronizedList(new ArrayList());
		List ll = Collections.synchronizedList(new LinkedList());
	}

}
