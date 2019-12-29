package com.example.demo.netty.mych2test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

	private final int port;
	
	public EchoServer (int port) {
		this.port = port;
	}
	
	public void start() throws InterruptedException {
		final EchoServerHandler serverHandler = new EchoServerHandler();
		
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group);
			b.channel(NioServerSocketChannel.class);
			b.localAddress(port);
			b.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(group, serverHandler);
				}
			});
			ChannelFuture fu = b.bind().sync();
			fu.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully().sync();
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		EchoServer server = new EchoServer(9999);
		System.out.println("服务即将启动");
		server.start();
		System.out.println("服务器关闭");

	}

}
