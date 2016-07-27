package com.lg.util;

public class TimeUtil {

	public static void sleep(int s){
		try {
			Thread.sleep(s*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
