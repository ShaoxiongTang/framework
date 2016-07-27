package com.lg.shiro;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.SimpleByteSource;


public class MD5Test {

	public static void main(String[] args) throws Exception{
		testPasswordMatcher();
	}
	
	public static void testPasswordMatcher(){
		PasswordService passwordService=new DefaultPasswordService();
		System.out.println(passwordService.encryptPassword("456"));
	}
	
	public static void generatePassword(){
		Hash hash=new SimpleHash("MD5", new SimpleByteSource("123"),new SimpleByteSource("www"),2);
		System.out.println(hash.toHex());
	}
	
	public static void testMd5() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md5=MessageDigest.getInstance("MD5");
		String str="lg";
		md5.reset();
		byte[] ret=md5.digest(str.getBytes("UTF-8"));
		System.out.println(Hex.encodeToString(ret));
	}
}
