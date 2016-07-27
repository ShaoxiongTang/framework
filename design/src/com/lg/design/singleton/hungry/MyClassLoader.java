package com.lg.design.singleton.hungry;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;


public class MyClassLoader extends ClassLoader{
	
	private String name;
	private String classPath;
	
	public MyClassLoader(String name){
        super(null);
        this.name = name;
    }
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b=getClassBytes(name);
		return this.defineClass(name, b,0,b.length);
	}

	private byte[] getClassBytes(String name) {
		String classFullPath=classPath+"/"+name.replace(".","/")+".class";
		byte[] data=null;
		try {
			FileInputStream fileInputStream=new FileInputStream(classFullPath);
			ByteArrayOutputStream out=new ByteArrayOutputStream();
			IOUtils.copy(fileInputStream,out);
			data=out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

}
