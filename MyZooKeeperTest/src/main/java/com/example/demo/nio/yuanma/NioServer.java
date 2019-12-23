package com.example.demo.nio.yuanma;


/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 类说明：nio通信服务端
 */
public class NioServer {

    private static NioServerHandle nioServerHandle;

    public static void start(){
        if(nioServerHandle !=null)
            nioServerHandle.stop();
        nioServerHandle = new NioServerHandle(12345);
        new Thread(nioServerHandle,"Server").start();
    }
    public static void main(String[] args){
        start();
    }

}
