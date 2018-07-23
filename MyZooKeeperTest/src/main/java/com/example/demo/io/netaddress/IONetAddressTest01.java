package com.example.demo.io.netaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IONetAddressTest01 {

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("www.baidu.com");
			InetAddress addressIp = InetAddress.getByName("61.135.169.125");
			InetAddress local = InetAddress.getLocalHost();
			System.out.println(address.getHostAddress());
			System.out.println(address);
			System.out.println("IP   --  "+addressIp.getCanonicalHostName());
			System.out.println("IP   --  "+addressIp);
			System.out.println("localhsost   --  "+local.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
