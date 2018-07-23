package com.example.demo.io.netaddress;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IONetworkInterfaceTest01 {

	public static void main(String[] args) {
		
		try {
			InetAddress address = InetAddress.getByName("127.0.0.1");
			
			NetworkInterface network = NetworkInterface.getByInetAddress(address);
			
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while(en.hasMoreElements()){
				NetworkInterface in = en.nextElement();
				System.out.println(in);
			}
			
			if(network != null){
				System.out.println("---this is not null ");
			}else{
				System.out.println("---this is null !");
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		
	}

}
