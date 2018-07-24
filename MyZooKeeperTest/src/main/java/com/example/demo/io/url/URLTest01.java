package com.example.demo.io.url;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest01 {

	public static void main(String[] args) {
		try {
			URL url = new URL("");
			
			System.setProperty("", "");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
	}

}
