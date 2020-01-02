package com.example.demo.netty.myhttp.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class BusiHander extends ChannelInboundHandlerAdapter{
	private void send(String content, ChannelHandlerContext ctx, 
			HttpResponseStatus status) {
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
				Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));
		response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String result = "";
		FullHttpRequest httpRequest = (FullHttpRequest)msg;
		try {
			String path = httpRequest.uri();
			String body = httpRequest.content().toString(CharsetUtil.UTF_8);
			HttpMethod method = httpRequest.method();
			if(!"/test".equals(path)) {
				result = "非法请求:" + path;
				send(result,ctx,HttpResponseStatus.BAD_REQUEST);
				return;
			}
			if(HttpMethod.GET.equals(method)) {
				System.out.println("body:"+body);
				result = "Get request, Response="+RespConstant.getNews();
				send(result,ctx,HttpResponseStatus.OK);
			}
			if(HttpMethod.POST.equals(method)) {
				//...
				
			}
		}catch(Exception e) {
			System.out.println("处理请求失败");
			e.printStackTrace();
		}finally {
			httpRequest.release();
		}
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("连接客户端地址：" + ctx.channel().remoteAddress());
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
	}
	
	

}
