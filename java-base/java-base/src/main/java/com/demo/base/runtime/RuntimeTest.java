package com.demo.base.runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.junit.Test;

public class RuntimeTest {
	
	public static void main(String[] args) throws Exception{
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(args[0]);
		// any error message?
		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");
		// any output?
		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT");
		// kick them off
		errorGobbler.start();
		outputGobbler.start();
		// any error???
		int exitVal = proc.waitFor();
		System.out.println("ExitValue: " + exitVal); 
	}

	@Test
	public void test1() throws IOException{
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("javac");
		int exitVal = proc.exitValue();
		System.out.println("Process exitValue: " + exitVal);
	}
	
	@Test
	public void test2() throws Exception{
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("javac");
		int exitVal = proc.waitFor();
		System.out.println("Process exitValue: " + exitVal);
	}
	
	@Test
	public void test3() throws Exception{
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("javac");
		InputStream stderr = proc.getErrorStream();
		InputStreamReader isr = new InputStreamReader(stderr,Charset.forName("GBK"));
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		System.out.println("<ERROR>");
		while ( (line = br.readLine()) != null)
		System.out.println(line);
		System.out.println("</ERROR>");
		int exitVal = proc.waitFor();
		System.out.println("Process exitValue: " + exitVal);
	}
	
	@Test
	public void test4() throws Exception{
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("dir");
		InputStream stderr = proc.getInputStream();
		InputStreamReader isr = new InputStreamReader(stderr,Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		System.out.println("<OUTPUT>");
		while ( (line = br.readLine()) != null)
		System.out.println(line);
		System.out.println("</OUTPUT>");
		int exitVal = proc.waitFor();
		System.out.println("Process exitValue: " + exitVal);
	}
	
	@Test
	public void test5() throws Exception{
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("echo");
		// any error message?
		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");
		// any output?
		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT");
		// kick them off
		errorGobbler.start();
		outputGobbler.start();
		// any error???
		int exitVal = proc.waitFor();
		System.out.println("ExitValue: " + exitVal); 
	}
}

