package com.example.demo.io.url;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 完整的请求和接收返回参数类
 * @author zh
 *
 */
public class URLConnectionTest03 {

	public static void main(String[] args) {
		
		try {
			URL url = new URL("http://localhost:8080/pub-tradesearch-web/pubtrade/search/pucTradeOrder");
			URLConnection uc = url.openConnection();
			uc.setDoOutput(true);
			uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			OutputStream out = uc.getOutputStream();
			OutputStream buffered = new BufferedOutputStream(out);
			OutputStreamWriter wr = new OutputStreamWriter(buffered, "UTF-8");
			wr.write("billNo=201058695640828639692881");
			
			wr.flush();
			wr.close();
			
			InputStream in = uc.getInputStream();
			Reader reader = new InputStreamReader(in);
			int r=0;
			while((r=reader.read()) != -1) {
				System.out.print((char)r);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
