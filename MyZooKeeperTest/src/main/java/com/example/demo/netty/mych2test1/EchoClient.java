package com.example.demo.netty.mych2test1;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

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
			Bootstrap b = new Bootstrap(); /* 客户端启动必备*/
			b.group(group);
			b.channel(NioSocketChannel.class); /*指定使用Nio进行通讯*/
			b.remoteAddress(new InetSocketAddress(host, port)); /*配置远程服务器地址*/
			b.handler(new ChanneInitHander());
			ChannelFuture fu = b.connect().sync();  /*连接到远程节点，阻塞等待直到连接完成*/
			/*阻塞，直到channel关闭*/
			fu.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully().sync();
		}
	}
	
	public static class ChanneInitHander extends ChannelInitializer<Channel>{

		@Override
		protected void initChannel(Channel ch) throws Exception {
			ch.pipeline().addLast(new LineBasedFrameDecoder(1024)); //用分隔符，系统的
			ch.pipeline().addLast(new EchoClientHandle());
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
