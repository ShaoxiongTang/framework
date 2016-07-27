package com.lg.design.singleton.enumsingleton;

import java.lang.reflect.Constructor;



public class Test {

	public static void main(String[] args) throws Exception {
		Singleton singleton1=Singleton.instance;
		Singleton singleton2=Singleton.instance;
		
		Constructor<Singleton> constructor=Singleton.class.getDeclaredConstructor(String.class,int.class);
		//Constructor<Singleton> constructor=Singleton.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		Singleton singleton3=constructor.newInstance("otherInstance",9);
		//Singleton singleton3=constructor.newInstance();
		
		System.out.println(singleton1);
		System.out.println(singleton2);
		System.out.println(singleton3);
		System.out.println(singleton1==singleton2);
		System.out.println(singleton1==singleton3);
		
	}

}
