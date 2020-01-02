package com.example.demo.netty.myhttp.client;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import com.example.demo.netty.myhttp.server.HttpServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpVersion;

public class HttpClient {
	
	public void connect(String host,int port) {
		 
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new HttpClientCodec());
					ch.pipeline().addLast(new HttpObjectAggregator(10 * 1024 * 1024));
					ch.pipeline().addLast("decompressor", new HttpContentDecompressor());
					ch.pipeline().addLast(new HttpClientInboundHandler());
				}
				
			});
			
			ChannelFuture fu = b.connect(host, port).sync();
			
			URI uri = new URI("/test");
			String body = "Hello";
			DefaultFullHttpRequest request = new DefaultFullHttpRequest(
					HttpVersion.HTTP_1_1,
					HttpMethod.GET,
					uri.toASCIIString(),
					Unpooled.wrappedBuffer(body.getBytes("UTF-8")));
			
			//构建http请求
			request.headers().set(HttpHeaderNames.HOST,host);
			request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
			request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
			//发送http请求
			fu.channel().write(request);
			fu.channel().flush();
			fu.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally {
			group.shutdownGracefully();
		}
		
	}

	public static void main(String[] args) {
		HttpClient client = new HttpClient();
		client.connect("127.0.0.1", HttpServer.port);
	}

}
