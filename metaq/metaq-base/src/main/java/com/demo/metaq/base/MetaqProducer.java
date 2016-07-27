package com.demo.metaq.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.taobao.metamorphosis.exception.MetaClientException;
import com.taobao.metamorphosis.utils.ZkUtils.ZKConfig;

public class MetaqProducer {
	
	private static Logger logger=LoggerFactory.getLogger(MetaqProducer.class);
	
	private static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");

	public static void main(String[] args) throws MetaClientException{
		final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZKConfig zkConfig = new ZKConfig();
        zkConfig.zkConnect = "192.168.49.208:2181,192.168.49.207:2181,192.168.49.206:2181";
        zkConfig.zkRoot="/ops/sysdev/metaq";
        metaClientConfig.setZkConfig(zkConfig);
        MessageSessionFactory sessionFactory = new MetaMessageSessionFactory(metaClientConfig);
        MessageProducer producer = sessionFactory.createProducer();
        final String topic = "test1";
        producer.publish(topic);
        Long count=0L;
        while (true) {
        	String line=format.format(new Date())+" index->"+count;
            try {
				SendResult sendResult = producer.sendMessage(new Message(topic,line.getBytes("UTF-8")));
				if (!sendResult.isSuccess()) {
					logger.error("Send message failed,error message:" + sendResult.getErrorMessage());
				}else {
					logger.info("Send message successfully,sent to " +sendResult.getPartition());
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
            count++;
        }
	}
}
