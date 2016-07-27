package com.demo.dubbo.demo.spi;

import java.util.ServiceLoader;

import org.junit.Test;

import com.alibaba.dubbo.common.ExtensionLoader;
import com.alibaba.dubbo.remoting.Transporter;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import com.demo.dubbo.demo.spi.service.HelloService;

public class JdkSpiTest {
	
	
	@Test
	public void testSpi(){
		ServiceLoader<HelloService> helloServiceLoader=ServiceLoader.load(HelloService.class);
		for(HelloService item:helloServiceLoader){
			item.hello();
		}
		for(HelloService item:helloServiceLoader){
			item.hello();
		}
		System.out.println("over");
	}
	
	@Test
	public void testDubboExtensionLoader(){
		ExtensionLoader<Protocol> protocolLoader=ExtensionLoader.getExtensionLoader(Protocol.class);
		Protocol  protocol=protocolLoader.getAdaptiveExtension();
		//此时的protocol也仅仅是不确定的，只有运行时根据参数动态决定采用哪一个Protocol实现
		protocol.export(null);

		Protocol dubboProtocol=protocolLoader.getExtension(DubboProtocol.NAME);
		protocolLoader.getExtension(protocolLoader.getDefaultExtensionName());
		protocolLoader.getDefaultExtension();
		//这两个是等价的
	}
	
	@Test
	public void testDubboTransporterExtensionLoader(){
		ExtensionLoader<Transporter> transporterLoader=ExtensionLoader.getExtensionLoader(Transporter.class); 
		transporterLoader.getAdaptiveExtension();
	}
}
