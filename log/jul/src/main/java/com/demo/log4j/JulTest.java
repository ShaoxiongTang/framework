package com.demo.log4j;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JulTest {

	private static final Logger logger=Logger.getLogger(JulTest.class.getName());
	
	public static void main(String[] args){
		logger.info("jul log a msg");
		logger.log(Level.WARNING,"jul waring a msg");
	}
}
