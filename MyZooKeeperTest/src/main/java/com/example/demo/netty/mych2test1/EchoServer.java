package com.example.demo.netty.mych2test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	
	private static int port;
	public EchoServer(int port) {
		this.port = port;
	}

	public void start() throws InterruptedException {
		ServerBootstrap b = new ServerBootstrap();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			b.group(group);
			b.channel(NioServerSocketChannel.class);
			b.localAddress(port);
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(group,new EchoServerHandler());
				}
			});
			ChannelFuture f = b.bind().sync();
			f.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) {
		EchoServer server = new EchoServer(12345);
		try {
			server.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
