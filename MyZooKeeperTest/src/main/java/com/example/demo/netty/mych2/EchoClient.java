package com.example.demo.netty.mych2;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty 客户端
 * @author zhanghao
 *
 */
public class EchoClient {

	private final int port;
	private final String host;
	
	public EchoClient(int port, String host) {
		this.host = host;
		this.port = port;
	}
	
	public void start() throws InterruptedException{
		EventLoopGroup group = new NioEventLoopGroup();/*线程组*/
		try {
			Bootstrap b = new Bootstrap();/*客户端启动必备*/
			b.group(group);/*把线程组交给启动类*/
			b.channel(NioSocketChannel.class);/*指明使用Nio进行网络通讯，共有4、5+种*/
			b.remoteAddress(new InetSocketAddress(host, port));/*配置远程服务器地址*/
			b.handler(new EchoClientHandler());
			ChannelFuture f = b.connect().sync();/*阻塞远程连接，直到连接完成为止*/
			/*阻塞，直到channel关闭*/
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new EchoClient(9999,"127.0.0.1").start();
	}

}
