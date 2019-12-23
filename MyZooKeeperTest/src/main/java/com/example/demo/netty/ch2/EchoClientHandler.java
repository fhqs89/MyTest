package com.example.demo.netty.ch2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

	/**
	 * 客户端读取到数据后干什么
	 * ctx 每一个Handler 都会有这个 表示上下文
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("Client accept:"+msg.toString(CharsetUtil.UTF_8));
		
	}

	/**
	 * 客户端被通知channel活跃以后，做事
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception{
//		super.channelActive(ctx);
		//往服务器写数据
		ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,Netty",CharsetUtil.UTF_8));
	}

	/**
	 * 统一的处理异常的方法
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	
}
