package com.example.demo.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.util.TestZkUtil;
import com.example.demo.service.TestZkOperateThreeService;


@Service
public class TestZkOperateThreeServiceImpl implements TestZkOperateThreeService,InitializingBean{

	@Value("${spring.data.solr.zk-host}")
	String hostPort;
	
	private ZooKeeper zk;
	
	private TestZkUtil zkUtil;
	
	public TestZkOperateThreeServiceImpl() {
		this.zkUtil = new TestZkUtil();
	}
	
	//节点名称
	private static final String NODE_NAME="/test_node";
	List<String> list = null;
	
	@Override
	public String createNode() {
		String result = "";
		try {
			result = zk.create(NODE_NAME, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			list = zk.getChildren("/", zkUtil);
			System.out.println(explainList(list));
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String deleteNode() {
		String result = "OK";
		try {
			zk.delete(NODE_NAME, 0);
			list = zk.getChildren("/", zkUtil);
			System.out.println(explainList(list));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String isExistNode() {
		String result = "OK";
		try {
			Stat stat = zk.exists(NODE_NAME, zkUtil);
			list = zk.getChildren("/", zkUtil);
			System.out.println(explainList(list));
			result = JSONObject.toJSONString(stat);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		try {
//			zk = new ZooKeeper(hostPort,15000,zkUtil);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private String explainList(List<String> list2) {
		JSONObject json = new JSONObject();
		for(int i=0;i<list2.size();i++) {
			json.put(i+"",list2.get(i));
		}
		return json.toJSONString();
	}

}
