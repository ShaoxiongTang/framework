package com.demo.dubbo.server.start;

import java.io.IOException;

import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.bytecode.Wrapper;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.serialize.Serialization;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.ChannelHandler;
import com.alibaba.dubbo.remoting.Codec2;
import com.alibaba.dubbo.remoting.Server;
import com.alibaba.dubbo.remoting.Transporter;
import com.alibaba.dubbo.remoting.exchange.ExchangeServer;
import com.alibaba.dubbo.remoting.exchange.Exchanger;
import com.alibaba.dubbo.remoting.transport.AbstractClient;
import com.alibaba.dubbo.remoting.transport.AbstractEndpoint;
import com.alibaba.dubbo.remoting.transport.AbstractServer;
import com.alibaba.dubbo.remoting.transport.netty.NettyHandler;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.cluster.Directory;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboExporter;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboInvoker;

public class StartServer {
	
	NamespaceHandler namespaceHandler;
	BeanDefinitionParser beanDefinitionParser;
	ServiceBean<?> serviceBean;
	ServiceConfig<?> serviceConfig;
	ReferenceBean<?> reference;
	ReferenceConfig<?> referenceConfig;
	Protocol protocol;
	Transporter transporter;
	Wrapper wrapper;
	ProxyFactory proxyFactory;
	Directory<?> directory;
	RegistryFactory registryFactory;
	ExtensionLoader<?>  extensionLoader;
	DubboInvoker<?> dubboInvoker;
	Invoker<?> invoker;
	DubboExporter<?> dubboExporter;
	NettyHandler nettyHandler;
	AbstractEndpoint abstractEndpoint;
	Channel channel;
	ChannelHandler channelHandler;
	AbstractClient abstractClient;
	AbstractServer abstractServer;
	ExchangeServer exchangeServer;
	Exchanger exchanger;
	Serialization serialization;
	Codec2 codec2;
	Logger logger;
	Server server;

	public static void main(String[] args) throws IOException{
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-context.xml"});  
        context.start();  
        System.out.println("按任意键退出");  
        System.in.read();  
	}
}
