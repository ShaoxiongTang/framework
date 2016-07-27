package com.lg.design.singleton.lazy;

public final class Singleton {

	private static class SingletonHolder{
		public static Singleton instance=new Singleton();
	}
	
	private Singleton(){}
	
	public static  Singleton getInstance(){
		return SingletonHolder.instance;
	}
}
