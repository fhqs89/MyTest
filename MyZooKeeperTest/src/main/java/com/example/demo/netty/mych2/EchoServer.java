package com.example.demo.netty.mych2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	
	private final int port;
	
	public EchoServer(int port) {
		this.port = port;
	}
	
	public void start() throws InterruptedException{
		final EchoServerHandler serverHandler = new EchoServerHandler();
		/*线程组*/
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();/*服务端启动必备*/
			b.group(group)
			.channel(NioServerSocketChannel.class) /*指明使用nio进行通讯*/
			.localAddress(port)  /*指明服务器监听端口*/
			/*接收到请求，新启一个socket通信，也就是channel，每个channel都有自己的事件的handler*/
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(serverHandler);
				}
			});
			
			ChannelFuture f = b.bind().sync();/*绑定到端口，阻塞等到直到连接完成*/
			f.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int port = 9999;
		EchoServer echoServer = new EchoServer(port);
		System.out.println("服务即将启动");
		echoServer.start();
		System.out.println("服务器关闭");
	}

}
