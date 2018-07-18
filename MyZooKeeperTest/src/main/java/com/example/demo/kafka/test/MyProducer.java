package com.example.demo.kafka.test;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.security.JaasUtils;

import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;


public class MyProducer {
	
	public static void main(String args[]) {
//		createTopic();
		Properties props = new Properties();
		props.setProperty("bootstrap.servers","localhost:9092");
		props.put("acks", "1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		//创建生产对象
		Producer<String,String> producer = new KafkaProducer<String,String>(props);
		ProducerRecord<String,String> record = null;
		try {
			for(int i=0;i<100;i++) {
				record = new ProducerRecord<String, String>("mykafka",i+"","test-kafka-"+i);
				producer.send(record);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		producer.close();
	}
	
	public static void createTopic() {
		
		ZkUtils zkUtils = ZkUtils.apply("localhost:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
		// 创建一个单分区单副本名为t1的topic
		AdminUtils.createTopic(zkUtils, "mykafka", 1, 1, new Properties());
		zkUtils.close();
		
	}

}
