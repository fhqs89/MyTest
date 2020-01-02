package com.example.demo.netty.myhttp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class ServerHandlerInit extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline ph = ch.pipeline();
		ph.addLast("encode", new HttpResponseEncoder());
		ph.addLast("decode", new HttpRequestDecoder());
		ph.addLast("aggre",new HttpObjectAggregator(10 * 1024 * 1024));
		ph.addLast("compressor",new HttpContentCompressor());
		ph.addLast("busi", new BusiHander());
		
	}

}
