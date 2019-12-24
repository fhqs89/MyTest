package com.example.demo.netty.mych2;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * 自己的业务类
 * @author zhanghao
 *
 */
/*指明我这个Handler可以在多个channel之间共享，意味着这个实现必须是线程安全的*/
@ChannelHandler.Sharable  
//无状态
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

	/**
	 * 服务端读取到网络数据后到处理
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;/*netty实现的缓冲区*/
		System.out.println("Server Accept:" + in.toString(CharsetUtil.UTF_8));
		ctx.write(in);
	}

	/**
	 * 服务端读取完成网络数据后的处理
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) //flush掉所有的数据
			.addListener(ChannelFutureListener.CLOSE);//当flush完成后，关闭连接
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
