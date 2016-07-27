package com.demo.quartz.base;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartServer {
	
	private static final Logger logger=LoggerFactory.getLogger(StartServer.class);
	
	private static volatile boolean running = true;

	public static void main(String[] args) throws IOException{
		try {
			final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] {"spring-context.xml","spring-jdbc.xml"});  
			context.start();  
			
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					try {
						try {
							if (context != null) {
								context.stop();
								context.close();
							}
						} catch (Throwable e) {
							logger.error(e.getMessage());
						}
						logger.info("service  stopped!");
					} catch (Throwable t) {
						logger.error(t.getMessage());
					}
					synchronized (StartServer.class) {
						running = false;
						StartServer.class.notify();
					}
				}
			});
		} catch (Exception e1) {
			logger.error(e1.getMessage());
		}
        
        synchronized (StartServer.class) {
			while (running) {
				try {
					StartServer.class.wait();
				} catch (Throwable e) {}
			}
		}
        
	}
}
