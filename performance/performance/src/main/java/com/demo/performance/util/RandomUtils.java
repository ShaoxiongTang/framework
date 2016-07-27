package com.demo.performance.util;

import java.util.Random;

public class RandomUtils {

	private static Random random = new Random(20);
	
	public static int getRandom(){
		return random.nextInt(100);
	}
}
