package com.demo.log4j;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		test2();
	}
	
	private static void test2(){
		long currentTime=System.currentTimeMillis();
		System.out.println(Long.toBinaryString(currentTime));
		
		long a=Long.parseLong("11100111011100110110010101110100111100011",2);
		a=a<<24;
		if(a>0){
			System.out.println("a是一个正数");
		}else{
			System.out.println("a是一个负数");
		}
		a=a>>8;
		System.out.println(Long.toBinaryString(a));
	}
	
	private static void test1(){
		long id=123456789;
		
		byte[] byte1=generatePasswd(id);
		byte[] byte2=generatePasswd(id);
		for(int i=0;i<byte1.length;i++){
			System.out.println(byte1[i]+" PK "+byte2[i]);
			if(byte1[i]!=byte2[i]){
				System.out.println("两者不一样");
			}
		}
		System.out.println("比较结束");
	}
	
	private static byte[] generatePasswd(long id) {
        Random r = new Random(id ^ 0XB3415C00L);
        byte p[] = new byte[16];
        r.nextBytes(p);
        return p;
    }

}
