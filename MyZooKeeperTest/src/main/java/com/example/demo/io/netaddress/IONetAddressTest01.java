package com.example.demo.io.netaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IONetAddressTest01 {

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("www.baidu.com");
			InetAddress addressIp = InetAddress.getByName("115.239.210.27");
			System.out.println(address.getHostAddress());
			System.out.println(address);
			System.out.println("IP   --  "+addressIp.getHostName());
			System.out.println("IP   --  "+addressIp);
			System.out.println("localhsost   --  "+InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
