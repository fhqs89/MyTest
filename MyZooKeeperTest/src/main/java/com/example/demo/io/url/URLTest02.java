package com.example.demo.io.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 可能不同的运行平台会有差异，比如win和macOS
 * 
 * 其实JDK是支持 RMI 和 JDBC 的， 只不过这两个协议分别通过java.rmi和java.sql包来支持，
 * 而无法跟其他协议一样通过URL来访问。
 * 
 * @author zh
 *
 */
public class URLTest02 {

	public static void main(String[] args) {
		
		//超文本传输协议
		testURLProtocol("http://www.baidu.com");
		
		//安全http
		testURLProtocol("https://www.baidu.com");
		
		//文件传输协议
		testURLProtocol("ftp://ibiblio.org/pub/java/");
		
		//简单邮件传输协议
		testURLProtocol("mailto:test@sina.com");
		
		//telnet
		testURLProtocol("telnet://debner.poly.edu/");
		
		//本地文件访问
		testURLProtocol("file:///etc/passwd");
	
		//gopher
		testURLProtocol("gopher://gopher.anc.org.za/");
	
		//轻量级目录访问协议
		testURLProtocol("ldap://ldap.itd.umich.edu/o=University%200f%20Michigan,c=US");
	
		//JAR
		testURLProtocol("jar:http://cafeaulait.org/books/javaio/.../StreamCopier.class");
	
		//NFS,网络文件系统
		testURLProtocol("nfs://utopia.poly.edu/usr/tmp/");
	
		//JDBC的定制协议
		testURLProtocol("jdbc:mysql://luna.ibiblio.org:3306/NEWs");
	
		//rmi，远程方法调用协议
		testURLProtocol("rmi://ibiblio.org/RenderEngine");
		
		//HotJava的定制协议
		testURLProtocol("doc:/UsersGuide/release.html");
		testURLProtocol("netdoc:/UsersGuide/release.html");
		testURLProtocol("systemresource://www.adc.org/+/index.html");
		testURLProtocol("verbatiom:http://www.adc.org/");
	}
	
	public static void testURLProtocol(String testURL){
		
		String protocol = testURL.split(":")[0];
		try {
			URL url = new URL(testURL);
			System.out.println(protocol + " is allowed");
		} catch (MalformedURLException e) {
			System.out.println(protocol + " is disallowed -------");
		}
		
	}

}
