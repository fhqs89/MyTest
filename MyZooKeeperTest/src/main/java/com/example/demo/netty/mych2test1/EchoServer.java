package com.example.demo.netty.mych2test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

public class EchoServer {
	
	private static int port;
	public EchoServer(int port) {
		this.port = port;
	}

	public void start() throws InterruptedException {
		ServerBootstrap b = new ServerBootstrap();/*服务端启动必备*/
		EventLoopGroup group = new NioEventLoopGroup();/*线程组*/
		try {
			b.group(group);
			/*指定使用Nio进行网络通讯*/
			b.channel(NioServerSocketChannel.class);
			b.localAddress(port);/*绑定到端口*/
			/*接收到连接请求，新启一个socket通讯，也就是channel，每个channel都有自己的处理handler
			 * */
			b.childHandler(new ChanneInitHander());
			ChannelFuture f = b.bind().sync();/*阻塞等待，直到绑定端口完成*/
			/*关闭channel，阻塞等待直到完成*/
			f.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully().sync();
		}
	}
	public static class ChanneInitHander extends ChannelInitializer<Channel>{

		@Override
		protected void initChannel(Channel ch) throws Exception {
			ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
			ch.pipeline().addLast(new EchoServerHandler());
		}
		
	}
	
	public static void main(String[] args) {
		EchoServer server = new EchoServer(12345);
		try {
			System.out.println();
			server.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
