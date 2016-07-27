package com.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {
	
	private static final Logger logger=LoggerFactory.getLogger(LogbackTest.class);
	
	public static void main(String[] args){
		if(logger.isDebugEnabled()){
			logger.debug("slf4j-logback debug message");
		}
		if(logger.isInfoEnabled()){
			logger.info("slf4j-logback info message");
		}
		if(logger.isTraceEnabled()){
			logger.trace("slf4j-logback trace message");
		}
	}
}
