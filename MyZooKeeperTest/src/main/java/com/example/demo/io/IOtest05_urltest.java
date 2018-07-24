package com.example.demo.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class IOtest05_urltest {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.baidu.com");
			
			InputStream in = url.openStream();
			in = new BufferedInputStream(in);
//			byte[] by = new byte[in.available()];
//			in.read(by);
//			System.out.println(new String(by));
			
			int c;
			while((c = in.read()) != -1){
				System.out.print((char)c);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
