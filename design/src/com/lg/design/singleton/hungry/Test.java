package com.lg.design.singleton.hungry;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Constructor;

public class Test {

	public static void main(String[] args) throws Exception {
		testClassLoader();
	}
	
	@SuppressWarnings({"unchecked" })
	public static void testClassLoader() throws Exception{
		Singleton singleton=Singleton.getInstance();
		
		MyClassLoader myClassLoader=new MyClassLoader("myClassLoader");
		myClassLoader.setClassPath("D:/important");
		Class singletonClass=myClassLoader.findClass("com.lg.design.singleton.hungry.Singleton");
		System.out.println("singletonClass.getClassLoader() : "+singletonClass.getClassLoader());
		
		System.out.println("Singleton.class==singletonClass : "+(Singleton.class==singletonClass));
		System.out.println("Singleton.class.equals(singletonClass) : "+(Singleton.class.equals(singletonClass)));
		
		Constructor constructor1=Singleton.class.getDeclaredConstructor();
		Constructor constructor2=Singleton.class.getDeclaredConstructor();
		Constructor constructor3=singletonClass.getDeclaredConstructor();
		System.out.println("constructor1==constructor2 : "+(constructor1==constructor2));
		System.out.println("constructor1.equals(constructor2) : "+constructor1.equals(constructor2));
		System.out.println("constructor1==constructor : "+(constructor1==constructor3));
		System.out.println("constructor1.equals(constructor3) : "+constructor1.equals(constructor3));
		
		constructor1.setAccessible(true);
		Object singleton1=constructor1.newInstance();
		constructor3.setAccessible(true);
		Object singleton3=constructor3.newInstance();
		
		System.out.println("singleton : "+singleton);
		System.out.println("singleton1 : "+singleton1);
		System.out.println("singleton3 : "+singleton3);
		System.out.println("singleton1==singleton3 : "+(singleton1==singleton3));
	}
	
	public static void testSerializable() throws Exception{
		Singleton singleton1=Singleton.getInstance();
		
		FileOutputStream fileOut=new FileOutputStream("D:\\singleton.txt");
		ObjectOutputStream out=new ObjectOutputStream(fileOut);
		out.writeObject(singleton1);
		out.close();
		
		FileInputStream fileInputStream=new FileInputStream("D:\\singleton.txt");
		ObjectInputStream in=new ObjectInputStream(fileInputStream);
		Singleton singleton2=(Singleton)in.readObject();
		in.close();
		
		System.out.println(singleton1);
		System.out.println(singleton2);
		System.out.println(singleton1==singleton2);
		
	}
	
	public static void testReflect()  throws Exception{
		Singleton singleton1=Singleton.getInstance();
		Singleton singleton2=Singleton.getInstance();
		
		Constructor<Singleton> constructor=Singleton.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		Singleton singleton3=constructor.newInstance();
		
		System.out.println(singleton1);
		System.out.println(singleton2);
		System.out.println(singleton3);
		System.out.println(singleton1==singleton2);
		System.out.println(singleton1==singleton3);
	}

}
