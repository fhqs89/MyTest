package com.example.demo.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AioChannelWriteHandler implements CompletionHandler<Integer,ByteBuffer> {

	private AsynchronousSocketChannel clientChannel;
	private CountDownLatch latch;//在此处就是防止应用程序退出
	
	public AioChannelWriteHandler (AsynchronousSocketChannel clientChannel, 
			CountDownLatch latch) {
		this.clientChannel = clientChannel;
		this.latch = latch;
	}
	
	@Override
	public void completed(Integer result, ByteBuffer buffer) {
		
		if(buffer.hasRemaining()){//是否写完
			clientChannel.write(buffer ,buffer, this);
		}else {
			//读取数据
			ByteBuffer readBuffer = ByteBuffer.allocate(1024);
			clientChannel.read(readBuffer, readBuffer, 
					new AioChannelWriteHandler(clientChannel, latch));
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		System.err.println("数据发送失败。。。");
		try {
			clientChannel.close();
			latch.countDown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


}
