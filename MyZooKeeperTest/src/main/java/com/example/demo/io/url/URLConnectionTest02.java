package com.example.demo.io.url;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.MalformedURLException;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLConnectionTest02 {

	public static void main(String[] args) {
		try {
			URL baidu = new URL("http://www.baidu.com");
			
			URLConnection uc = baidu.openConnection();
			
			try(InputStream in = uc.getInputStream()){
				InputStream buffer = new BufferedInputStream(in);
				Reader reader = new InputStreamReader(in);
				int c;
				while((c=reader.read()) != -1) {
					System.out.print((char)c);
				}
				
			}
			
			ResponseCache a = new ResponseCache() {

				@Override
				public CacheResponse get(URI uri, String rqstMethod, Map<String, List<String>> rqstHeaders)
						throws IOException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public CacheRequest put(URI uri, URLConnection conn) throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
				
			};
			
			ResponseCache.setDefault(a);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
