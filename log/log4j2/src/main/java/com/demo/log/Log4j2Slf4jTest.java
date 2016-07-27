package com.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4j2Slf4jTest {

	private static Logger logger=LoggerFactory.getLogger(Log4j2Slf4jTest.class);
	
	public static void main(String[] args){
		if(logger.isTraceEnabled()){
			logger.trace("slf4j-log4j2 trace message");
		}
		if(logger.isDebugEnabled()){
			logger.debug("slf4j-log4j2 debug message");
		}
		if(logger.isInfoEnabled()){
			logger.info("slf4j-log4j2 info message");
		}
	}
}
