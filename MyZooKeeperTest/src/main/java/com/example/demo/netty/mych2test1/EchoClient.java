package com.example.demo.netty.mych2test1;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

	public final String host;
	public final int port;
	
	public EchoClient(String host,int port) {
		this.host = host;
		this.port = port;
	}
	
	public void start() throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioSocketChannel.class);
			b.remoteAddress(new InetSocketAddress(host, port));
			b.handler(new EchoClientHandle());
			ChannelFuture fu = b.connect().sync();
			fu.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) {
		EchoClient client = new EchoClient("127.0.0.1",12345);
		try {
			client.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
