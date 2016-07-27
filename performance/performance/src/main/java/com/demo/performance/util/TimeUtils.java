package com.demo.performance.util;

public class TimeUtils {

	public static void sleep(long ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
