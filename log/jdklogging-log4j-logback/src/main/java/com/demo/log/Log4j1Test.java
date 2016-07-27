package com.demo.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

public class Log4j1Test {
	
	private static final String configPath="properties/log4j.properties";
	
	private static final String configPath2="log4j.properties";
	
	static{
		PropertyConfigurator.configure(Log4j1Test.class.getClassLoader().getResource(configPath2));
		PropertyConfigurator.configure(Loader.getResource(configPath));
	}

	private static final Logger logger=Logger.getLogger(Log4j1Test.class);
	
	public static void main(String[] args){
		if(logger.isTraceEnabled()){
			logger.debug("log4j trace message");
		}
		if(logger.isDebugEnabled()){
			logger.debug("log4j debug message");
		}
		if(logger.isInfoEnabled()){
			logger.debug("log4j info message");
		}
	}
	
}
