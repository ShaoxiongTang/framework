package com.demo.kafka.base;

import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.apache.kafka.common.utils.Utils;

import kafka.api.LeaderAndIsr;
import kafka.cluster.Partition;
import kafka.controller.KafkaController;
import kafka.controller.KafkaController.SessionExpirationListener;
import kafka.coordinator.GroupCoordinator;
import kafka.javaapi.producer.Producer;
import kafka.network.ConnectionQuotas;
import kafka.network.Processor;
import kafka.network.SocketServer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.server.KafkaServer;
import kafka.server.ZookeeperLeaderElector;
import kafka.utils.ReplicationUtils;
import kafka.utils.ZkUtils;


public class KafkaProducer {
	
	kafka.Kafka kafka;
	SocketServer socketServer;
	Utils utils;
	ConnectionQuotas connectionQuotas;
	Processor processor;
	Partition partition;
	LeaderAndIsr leaderAndIsr;
	ReplicationUtils replicationUtils;
	KafkaController kafkaController;
	SessionExpirationListener sessionExpirationListener;
	ZkUtils zkUtils;
	ZkClient zkClient;
	ZookeeperLeaderElector zookeeperLeaderElector;
	KafkaServer kafkaServer;
	GroupCoordinator groupCoordinator;

	public static void main(String[] args){
		Properties props=new Properties();
		props.setProperty("metadata.broker.list","192.168.126.131:9092");
		props.setProperty("serializer.class","kafka.serializer.StringEncoder");
		props.setProperty("key.serializer.class","kafka.serializer.StringEncoder");
		String topic="test";
		ProducerConfig config = new ProducerConfig(props);
		Producer<String,String> producer=new Producer<String,String>(config);
		Long count=0L;
		while(true){
			KeyedMessage<String, String> msg = new KeyedMessage<String, String>(topic,"一一"+count);
			producer.send(msg);
			count++;
		}
		
	}
}
