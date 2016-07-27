package com.demo.log4j;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jSlf4JTest {
	
	private static final String configPath="properties/log4j.properties";
	
	private static final String configPath2="log4j.properties";
	
	static{
		PropertyConfigurator.configure(Log4jTest.class.getClassLoader().getResource(configPath));
		PropertyConfigurator.configure(Loader.getResource(configPath2));
	}

	private static Logger logger=LoggerFactory.getLogger(Log4jSlf4JTest.class);
	
	public static void main(String[] args){
		if(logger.isDebugEnabled()){
			logger.debug("slf4j-log4j debug message");
		}
		if(logger.isInfoEnabled()){
			logger.debug("slf4j-log4j info message");
		}
		if(logger.isTraceEnabled()){
			logger.debug("slf4j-log4j trace message");
		}
	}
}
