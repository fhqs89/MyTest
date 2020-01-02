package com.example.demo.netty.mych2test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
/*指明这个handler可以在多个handler中共享，意味这个实现必须线程安全*/
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

	/**
	 * 服务端读取到网络数据后的处理
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;/*netty缓冲区*/
		System.out.println("Server Accept:" + buf.toString(CharsetUtil.UTF_8));
		ctx.write(buf);
	}

	/**
	 * 服务端读取完成网络数据后的处理
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER). /*flush掉所有数据*/
			addListener(ChannelFutureListener.CLOSE);/*当flush完成后，关闭连接*/
	}
	
	/**
	 * 发生异常后的处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	
}
