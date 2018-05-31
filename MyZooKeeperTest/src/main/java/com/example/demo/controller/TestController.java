package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value="/myTest/")
public class TestController {

	@RequestMapping(method=RequestMethod.GET,value="/test")
	public String myTestController() {
		return "This is my Test";
	}
	
}
