package com.example.demo.netty.mych2test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

	/**
	 * 客户端读到数据后的操作
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("Client accept:" + msg.toString(CharsetUtil.UTF_8));
	}

	/**
	 * 客户端被通知channel活跃以后，做事
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception{
//		ctx.channel().eventLoop()
		ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,Netty",CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}
