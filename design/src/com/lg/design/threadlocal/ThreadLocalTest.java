package com.lg.design.threadlocal;

public class ThreadLocalTest {

	public static void main(String[] args){
		test2();
	}
	
	private static void test2(){
		Thread t1=new Thread(){

			@Override
			public void run() {
				fun1();
				fun2();
			}

			private void fun2() {
				System.out.println("在函数2中获取到当前线程的name数据为:"+ThreadUtil.getName());
			}

			private void fun1() {
				ThreadUtil.setName("my own name");
				System.out.println("函数1设置当前线程的name数据为:my own name");
			}
			
		};
		t1.start();
	}
	
	private static void test1(){
		Thread t1=new Thread(){

			@Override
			public void run() {
				System.out.println(this.getName()+"开始值:"+ThreadUtil.getName());
				ThreadUtil.setName("线程0");
				sleepTime(1000*10);
				System.out.println(this.getName()+"设定后:"+ThreadUtil.getName());
			}
			
		};
		Thread t2=new Thread(){

			@Override
			public void run() {
				sleepTime(1000*5);
				System.out.println(this.getName()+"开始值:"+ThreadUtil.getName());
				ThreadUtil.setName("线程1");
				System.out.println(this.getName()+"设定后:"+ThreadUtil.getName());
			}
			
		};
		t1.start();
		t2.start();
	}
	
	private static void sleepTime(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
