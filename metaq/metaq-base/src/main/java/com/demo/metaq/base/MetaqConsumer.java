package com.demo.metaq.base;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.cluster.Partition;
import com.taobao.metamorphosis.exception.MetaClientException;
import com.taobao.metamorphosis.utils.ZkUtils.ZKConfig;

public class MetaqConsumer {
	
	private static Logger logger=LoggerFactory.getLogger(MetaqConsumer.class);

	public static void main(String[] args) throws MetaClientException{
		final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZKConfig zkConfig = new ZKConfig();
        zkConfig.zkConnect = "192.168.49.208:2181,192.168.49.207:2181,192.168.49.206:2181";
        zkConfig.zkRoot="/ops/sysdev/metaq";
        metaClientConfig.setZkConfig(zkConfig);
        MessageSessionFactory sessionFactory = new MetaMessageSessionFactory(metaClientConfig);
        /*final String topic = "test1";
        final String group = "testlg-group1";*/
        final String topic = "mobilelog";
        final String group = "mobilelog-splunk-LG";
        MessageConsumer consumer = sessionFactory.createConsumer(new ConsumerConfig(group));
        consumer.subscribe(topic, 1024 * 1024 * 8, new MessageListener() {

            public void recieveMessages(Message message) {
            	Partition p=message.getPartition();
            	//System.out.println(p.getBrokerId()+";"+p.getPartition());
                try {
                	String msg=new String(message.getData(),"UTF-8");
                	if(StringUtils.containsIgnoreCase(msg, "hwsvcvisit")/* && msg.contains("1226")*/){
                		logger.info(msg);
                	}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
            }
            public Executor getExecutor() {
                return null;
            }
        });
        consumer.completeSubscribe();
	}
}
