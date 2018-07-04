package com.example.demo;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZkMainTest implements Watcher{

	
	public static void main(String[] args) {
		
		try {
			ZooKeeper zk = new ZooKeeper("10.157.46.126:2182",15000, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------");
	}

	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
