package com.example.demo.io.url;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionTest01 {

	public static void main(String[] args) {
		
		try {
			URL url = new URL("http://localhost:8080/pub-tradesearch-web/pubtrade/search/pucTradeOrder");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
//			OutputStream out = con.getOutputStream();
			InputStream in = con.getInputStream();
			Reader reader = new InputStreamReader(in);
//			StringBuffer sbr = new StringBuffer();
//			byte[] by = new byte[in.available()];
			int r = 0;
//			int i = 0;
			while((r=reader.read()) != -1) {
				System.out.print((char)r);
//				sbr.append((char)r);
//				by[i] = (byte) r;
//				i++;
			}
//			System.out.println();
//			System.out.println(new String(by));
//			System.out.println(sbr.toString());
			in.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
