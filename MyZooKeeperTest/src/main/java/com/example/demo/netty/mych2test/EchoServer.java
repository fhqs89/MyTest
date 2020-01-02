package com.example.demo.netty.mych2test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
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
		/*线程组*/
		EventLoopGroup group = new NioEventLoopGroup();
//		EventLoopGroup group2 = new EpollEventLoopGroup();//epoll 只用与Linux
		try {
			ServerBootstrap b = new ServerBootstrap(); /*服务端启动必备*/
			b.group(group);
			/*指明使用Nio进行网络通讯 5种*/
			b.channel(NioServerSocketChannel.class); 
//			b.channel(EpollServerSocketChannel.class);//epoll 只用与Linux
			/*指定监听端口*/
			b.localAddress(port);
			/*接收到连接请求，新启一个socket通讯，也就是channel，每个channel有自己的事件handler
			 * 
			 */
			b.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(group, serverHandler);
				}
			});
			ChannelFuture fu = b.bind().sync();/*绑定到端口，阻塞等待直到完成*/
			/*阻塞，直到channel关闭*/
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
