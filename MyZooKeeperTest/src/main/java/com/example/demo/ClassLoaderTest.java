package com.example.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderTest {

	public static void main(String[] args) throws MalformedURLException {
		URL[] url = new URL[]{new URL("")};
		URLClassLoader l = new URLClassLoader(url);
		Thread t = new Thread();
		ThreadLocal tl = new ThreadLocal();
//		ExtClassLoader al = new URLClassLoader();
		
	}

}
