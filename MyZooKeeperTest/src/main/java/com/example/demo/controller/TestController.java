package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.TestService;
import com.example.demo.service.TestThreadService;
import com.example.demo.service.TestZkOperateService;
import com.example.demo.service.TestZkOperateThreeService;
import com.example.demo.service.TestZkOperateTwoService;

@Controller
@ResponseBody
@RequestMapping(value="/myTest/")
public class TestController {

	@Autowired
	TestService testServer;
	
	@Autowired
	TestZkOperateService testZkOperateService;
	
	@Autowired
	TestZkOperateTwoService testZkOperateTwoService;
	
	@Autowired
	TestZkOperateThreeService testZkOperateThreeService;
	
	@Autowired
	TestThreadService testThreadService;
	
	@RequestMapping(method=RequestMethod.GET,value="/test")
	public String myTestController() {
		testServer.test();
		return "This is my Test";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="zk_o")
	public String zooKeeperTestOne(Integer operate) {
		
		if(operate == null) {
			return "please input operate";
		} else if(operate == 1) {
			return testZkOperateService.createNode();
		}else if(operate == 2) {
			return testZkOperateService.deleteNode();
		}else if(operate == 3) {
			return testZkOperateService.isExistNode();
		}else {
			return "operate error";
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="zk_t")
	public String zooKeeperTestTow(Integer operate) {
		
		if(operate == null) {
			return "please input operate";
		} else if(operate == 1) {
			return testZkOperateTwoService.createNode();
		}else if(operate == 2) {
			return testZkOperateTwoService.deleteNode();
		}else if(operate == 3) {
			return testZkOperateTwoService.isExistNode();
		}else {
			return "operate error";
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="zk_s")
	public String zooKeeperTestThree(Integer operate) {
		
		if(operate == null) {
			return "please input operate";
		} else if(operate == 1) {
			return testZkOperateThreeService.createNode();
		}else if(operate == 2) {
			return testZkOperateThreeService.deleteNode();
		}else if(operate == 3) {
			return testZkOperateThreeService.isExistNode();
		}else {
			return "operate error";
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="th")
	public String threadTest(Integer operate) {
		if(operate == null) {
			return "please input operate";
		} else if(operate == 1) {
			return testThreadService.test();
		}else {
			return "operate error";
		}
		
	}
}
