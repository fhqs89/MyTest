package com.example.demo.io.url;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;

public class URLConnectionTest04 {

	public static void main(String args[]){
		
		URL url;
		try {
			url = new URL("http://localhost:8080/pub-tradesearch-web/pubtrade/search/pucTradeOrder");
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setRequestMethod("POST");
			
			InputStream in = uc.getInputStream();
			Reader reader = new InputStreamReader(in);
			int r = 0;
			while((r=reader.read()) != -1) {
				System.out.print((char)r);
			}
			System.out.println();
			System.out.println(uc.getHeaderField(0));
			System.out.println(uc.getHeaderField(1));
			System.out.println(uc.getHeaderField(2));
			System.out.println(uc.getHeaderField(3));
			System.out.println(uc.getHeaderField(4));
			System.out.println(uc.getHeaderField(5));
			System.out.println(uc.getHeaderField(6));
			System.out.println(uc.getHeaderField(7));
			System.out.println(uc.getHeaderField(8));
			System.out.println(uc.getHeaderField(9));
//			Permission per = uc.getPermission();
//			if(per == null){
//				System.out.println("null");
//			}else{
//				System.out.println(per);
//			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
