package com.example.demo.netty.mych2test1;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandle extends SimpleChannelInboundHandler<ByteBuf>{

	private AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * 客户端读到数据后的操作
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("client Accept["+msg.toString(CharsetUtil.UTF_8)
        +"] and the counter is:"+counter.incrementAndGet());
	}

	/**
	 * 客户端被通知channel活跃以后，做事
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf msg = null;
		String request = "Mark,Lison,Peter,James,Deer"
                + System.getProperty("line.separator");
		for(int i=0;i<100;i++) {
			msg = Unpooled.buffer(request.length());
			msg.writeBytes(request.getBytes());
			ctx.writeAndFlush(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	

}
