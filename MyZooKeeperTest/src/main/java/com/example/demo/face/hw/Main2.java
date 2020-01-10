package com.example.demo.face.hw;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
		String msg = "nhrwlbcc8m7c5hih9mha"
				+    "lw98k0322wf2jjm47kk3"
				+    "ntm9snfrflzzundn7d60" //--
				+    "8usy049asxalzjk7izj6"
				+    "amcqhr8uubc04g52mcjb"
				+    "oj2fmge2l6iarizfu4yv"
				+    "e5o4i3srf5zgqbg82ckc"
				+    "otdeqp760mc9gzei5dzk" //--
				+    "5gj9x9yj05o3hle0ii64"
				+    "krkkp5i7blh7nbu3gu5v"
				+    "gi2scyn4yqx3z4vcjbyz"
				+    "hnqkh887izotjkg2l0mi" //--
				+    "t0k14vyn39"; //--
//		String[] msgs = msg.split("a");
		
//		int i = 0;
//		while(true) {
//			int count = msg.indexOf("b",i);
//			System.out.println("i="+i + "  count="+count);
//			i++;
//		}
		
//		System.out.println(getLength(msg,"t"));
		
		Scanner sc = new Scanner(System.in);
        String fMsg = "";
        String sMsg = "";
        int i = 0;
        while(sc.hasNext()){
            String mmsg = sc.nextLine();
            if(i%2 == 1){
                fMsg = mmsg;
            }else if(i%2 == 0){
                sMsg = mmsg;
                System.out.println(getLength(fMsg,sMsg));
            }
            i++;
        }
		
		
	}
	 public static int getLength(String fMsg, String sMsg){
	        if(fMsg == null || sMsg == null){
	            return 0;
	        }
	        if(fMsg.equals(sMsg)){
	            return 1;
	        }
	        if(fMsg.equals("")){
	            return 0;
	        }
	    
	        int i = 0;
	        String subMsg = fMsg;
	        while(true){
	            int index = subMsg.indexOf(sMsg);
	            if(index >= 0){
	            	int offset = sMsg.length();
	                subMsg = subMsg.substring(index + offset, subMsg.length());
	            }else{
	                return i;
	            }
	            i++;
	        }
	        
	    }

}
