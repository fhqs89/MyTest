package com.example.demo.netty.mych2test1;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
/*指明我这个handler可以在多个channel之间共享，意味这个实现必须是线程安全的*/
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

	private AtomicInteger counter = new AtomicInteger(0);
	/**
	 * 服务端读到网络数据后的处理
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;/*netty缓冲区*/
		String request = in.toString(CharsetUtil.UTF_8);
        System.out.println("Server Accept["+request
                +"] and the counter is:"+counter.incrementAndGet());
        String resp = "Hello,"+request+". Welcome to Netty World!"
                + System.getProperty("line.separator");
		ctx.writeAndFlush(Unpooled.copiedBuffer(resp.getBytes()));
	}

	/**
	 * 服务端读取完成网络数据后的处理
	 */
//	@Override
//	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) /*flush掉所有的数据*/
//			.addListener(ChannelFutureListener.CLOSE); /*当flush完成后，关闭连接*/
//	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	
}
