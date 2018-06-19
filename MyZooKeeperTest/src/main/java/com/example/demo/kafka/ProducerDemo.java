package com.example.demo.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerDemo {

	public static void main(String[] args) {
		boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");
		
		Properties props = new Properties();
		
		props.put("bootstarp.servers", "localhost:9092");
		props.put("client.id", "DemoProducer");//客户端id
		//消息的key和value都是字节数组，为了将java对象转化为字节数组，
		//可以配置“key.serializer” 和 "value.serializer" 两个序列化器，完成转化
		props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		
		//StringSerializer用来将String对象序列化成字节数组
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer producer = new KafkaProducer<>(props);//生产者核心类
		String topic = "test";//向指定的这个topic发送消息
		
		int messageNo = 1; //消息的key
		
		while(true) {
			String messageStr = "Message_"+ messageNo;//消息的value
			long startTime = System.currentTimeMillis();
			if(isAsync) {//异步发送消息
				//第一个参数是ProducerRecord类型的对象，封装类目标topic、消息的key、消息的value
				//第二个参数是一个CallBack对象，当生产者接收到kafka发来的ACK确认消息的时候，会调用此
				//CallBack对象的onCompletion()方法，实现回调功能
				producer.send(new ProducerRecord<>(topic,messageNo,messageStr),
						new DemoCallBack(startTime,messageNo,messageStr));
			}else {//同步消息
				try {
					//KafkaProducer.send方法的返回值类型是Future<RecordMetadata>
					//这里通过Future.get()方法，阻塞当前线程，等待kafka服务端的ACK响应
					producer.send(new ProducerRecord<>(topic,messageNo,messageStr)).get();
					System.out.println("send message : (" + messageNo + ","+messageStr+")");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			++messageNo;//递增的消息key
		}
	}
}

class DemoCallBack implements Callback{//回调对象
	
	private final long startTime;//开始发送的时间戳
	private final int key; //消息的key
	private final String message; //消息的value
	
	public DemoCallBack(long startTime,int key,String message) {
		this.startTime = startTime;
		this.key = key;
		this.message = message;
	}
	
	/**
	 * 生产者成功发送消息，收到kafka服务端发来的ACK确认消息后，会调用此回调函数
	 * @param metadata 生产者发送的消息的元数据，如果发送过程中出现异常，此参数为null
	 * @param exception 发送过程中的出现的异常，如果发送成功，则此参数为null
	 */
	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		long elapsedTime = System.currentTimeMillis() - startTime;
		if(metadata != null) {
			//RecordMetadata中包含来分区信息、offset信息等
			System.out.println("message ("+key+", "+message+" ) sent to partition( " 
			+metadata.partition() + "),"+"offset("+metadata.offset()+" ) in "+elapsedTime + " ms");
		}else {
			exception.printStackTrace();
		}
		
		
	}
	
}

