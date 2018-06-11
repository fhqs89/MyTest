package com.example.demo.controller.util;


import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import com.alibaba.fastjson.JSONObject;

public class TestZkUtil implements Watcher{
	
	@Override
	public void process(WatchedEvent event) {
		System.out.println("333==   "+JSONObject.toJSONString(event));
	}

}
