package com.demo.log4j;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class JulSlf4jLog4jTest {
	
	static{
		SLF4JBridgeHandler.install();
	}
	
	private static final Logger logger=Logger.getLogger(JulSlf4jLog4jTest.class.getName());
	
	public static void main(String[] args){
		logger.log(Level.FINEST,"jul trace a msg");
		logger.log(Level.FINE,"jul debug a msg");
		logger.log(Level.INFO,"jul info a msg");
		logger.log(Level.WARNING,"jul waring a msg");
	}
}
