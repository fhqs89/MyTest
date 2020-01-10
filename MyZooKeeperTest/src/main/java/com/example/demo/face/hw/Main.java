package com.example.demo.face.hw;

import java.util.Scanner;

public class Main{
    public static void main(String args[]){
    	Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
        	String msg = scanner.nextLine();
            System.out.println(getLengths(msg));
        }
    }
    
    public static int getLengths(String msg){
        if(msg == null && "".equals(msg)){
            return 0;
        }
        String[] msgs = msg.split(" ");
        String lastKey = msgs[msgs.length - 1];
        return lastKey.length();
    }
    
    public static int getLength(String msg){
        if(msg == null && "".equals(msg)){
            return 0;
        }
        
        int lastKey = msg.lastIndexOf(" ");
        if(lastKey < 0){
            return msg.length();
        }
        return msg.length() - lastKey - 1;
        
    }
    
}