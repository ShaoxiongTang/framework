package com.lg.design.threadlocal;

public class ThreadUtil {

	private static ThreadLocal<String> nameLocal=new ThreadLocal<String>();
	
	public static String getName(){
		return nameLocal.get();
	}
	
	public static void setName(String name){
		nameLocal.set(name);
	}
}
