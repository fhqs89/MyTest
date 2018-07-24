package com.example.demo.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class IOtest06_urltest {

	public static void main(String[] args) {
		
		try {
			URL url = new URL("http://www.baidu.com?name=zhangsna#134we");
			
			URL urls = new URL("http://www.baidu.com:80?name=zhangsna#134we");
			
			try {
				System.out.println(URLEncoder.encode("http://www.baidu.com:80?name=zhangsna你#134we","UTF-8"));
				System.out.println(URLDecoder.decode("http%3A%2F%2Fwww.baidu.com%3A80%3Fname%3Dzhangsna%E4%BD%A0%23134we","UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(url.equals(urls)){
				System.out.println("equals");
			}
			
			try {
				URLConnection uc = url.openConnection();
				
//				InputStream in = uc.getInputStream();
//				in = new BufferedInputStream(in);
//				int c;
//				while((c = in.read()) != -1){
//					System.out.print((char)c);
//				}
//				in.close();
				
				Object o = uc.getContent();
				
				System.out.println("protocol = " + url.getProtocol());
				System.out.println("host = "+url.getHost());
				System.out.println("port = "+url.getPort());
				System.out.println("DEF port = "+url.getDefaultPort());
				System.out.println("ref = "+url.getRef());
				System.out.println("query = "+url.getQuery());
				System.out.println(o.getClass().getName());
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
