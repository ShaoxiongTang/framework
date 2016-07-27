package com.demo.metaq.base;

import org.apache.zookeeper.server.PrepRequestProcessor;

import com.taobao.gecko.core.util.DefaultExceptionMonitor;
import com.taobao.metamorphosis.client.consumer.SimpleFetchManager;

public class Server {

	DefaultExceptionMonitor defaultExceptionMonitor;
	com.taobao.metamorphosis.server.utils.MetaConfig config;
	com.taobao.metamorphosis.ServerStartup s;
	SimpleFetchManager simpleFetchManager;
	PrepRequestProcessor pp;
}
