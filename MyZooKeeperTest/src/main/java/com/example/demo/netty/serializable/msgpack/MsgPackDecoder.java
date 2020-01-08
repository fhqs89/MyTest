package com.example.demo.netty.serializable.msgpack;

import java.util.List;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf>{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		int length = msg.readableBytes();
		byte[] array = new byte[length];
		msg.getBytes(msg.readerIndex(), array, 0, length);
		MessagePack messagePack = new MessagePack();
		messagePack.read(array);
		out.add(messagePack.read(array,User.class));
	}

}
