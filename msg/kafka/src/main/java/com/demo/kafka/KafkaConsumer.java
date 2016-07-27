package com.demo.kafka;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerFetcherThread;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class KafkaConsumer extends Thread{
	
	ConsumerFetcherThread afsd;
	
	private final ConsumerConnector consumer;
	private final String topic;
	
	private ObjectMapper om = new ObjectMapper();
	
	public KafkaConsumer(String topic) {
		consumer = Consumer.createJavaConsumerConnector(createConsumerConfig());
		this.topic = topic;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		//KafkaConsumer consumer=new KafkaConsumer("syslogapp");
		//KafkaConsumer consumer=new KafkaConsumer("syslog");
		KafkaConsumer consumer=new KafkaConsumer("mobilelog");
		consumer.start();
	}
	
	public void run() {
		try {
			Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
			topicCountMap.put(topic, new Integer(1));
			Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
			KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
			ConsumerIterator<byte[], byte[]> it = stream.iterator();
			while (it.hasNext()){
				byte[] bytes=it.next().message();
				/*if(msg.contains("CN1DC1") || msg.contains("CN1DC3") || msg.contains("CN1DC6") || msg.contains("CN1DC2")
						|| msg.contains("CN1DC5") || msg.contains("CN1DC4") || msg.contains("CN5DC4") || msg.contains("CN5DC2")){
					System.out.println(msg);
				}*/
				Message msg = om.readValue(bytes, Message.class);
				System.out.println(msg.getLogType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ConsumerConfig createConsumerConfig() {
		Properties props = new Properties();
		props.put("zookeeper.connect", 			  "192.168.49.208:2181,192.168.49.207:2181,192.168.49.206:2181");
		//props.put("zookeeper.connect", 			  "10.2.27.122:2181");
		//props.put("zookeeper.connect", 			  "10.8.84.74:2181");
		//props.put("zookeeper.connect", 			  "10.8.84.108:2181");
		props.put("group.id", 					  "mobilelog-lg-g1");
		//props.put("auto.offset.reset", 			  "smallest");
		props.put("zookeeper.session.timeout.ms", "15000");
		props.put("zookeeper.sync.time.ms", 	  "3000");
		props.put("auto.commit.interval.ms", 	  "1000");
		return new ConsumerConfig(props);
	}
}
