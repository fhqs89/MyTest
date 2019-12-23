package com.example.demo.nio.yuanma;

import java.util.Scanner;


/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 类说明：nio通信客户端
 */
public class NioClient {

    private static NioClientHandle nioClientHandle;

    public static void start(){
        if(nioClientHandle !=null)
            nioClientHandle.stop();
        nioClientHandle = new NioClientHandle("127.0.0.1",12345);
        new Thread(nioClientHandle,"Client").start();
    }
    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception{
        nioClientHandle.sendMsg(msg);
        return true;
    }
    public static void main(String[] args) throws Exception {
        start();
        System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while(NioClient.sendMsg(scanner.next()));

    }

}
