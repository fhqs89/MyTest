package com.example.demo.netty.myhttp.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HttpServer {
	
	public static final int port = 6789;
	private static EventLoopGroup group = new NioEventLoopGroup();
	private static ServerBootstrap b = new ServerBootstrap();
	private static final boolean SSL = true;

	public static void main(String[] args) {
		try {
			b.group(group);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new ServerHandlerInit());
			//服务器端口监听
			ChannelFuture f = b.bind(port);
			System.out.println("服务端启动成功，端口:"+port);
			//监听服务器关闭监听
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			group.shutdownGracefully();
		}
		
	}

}
