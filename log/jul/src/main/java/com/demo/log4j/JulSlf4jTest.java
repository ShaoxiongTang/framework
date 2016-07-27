package com.demo.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JulSlf4jTest {
	
	private static final Logger logger=LoggerFactory.getLogger(JulSlf4jTest.class);
	
	public static void main(String[] args){
		if(logger.isDebugEnabled()){
			logger.debug("jul debug message");
		}
		if(logger.isInfoEnabled()){
			logger.info("jul info message");
		}
		if(logger.isWarnEnabled()){
			logger.warn("jul warn message");
		}
	}
}
