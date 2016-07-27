package com.lg.shiro;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;

import org.junit.Test;

public class JCATest {
	
	Provider provider;
	MessageDigest messagedigest;
	Key key;
	AlgorithmParameters algorithmParameters;
	AlgorithmParameterGenerator algorithmParameterGenerator;
	KeyPair keyPair;
	KeyPairGenerator keyPairGenerator;

	@Test
	public void test(){
		Provider[] providers=Security.getProviders();
		int i=1;
		for(Provider provider:providers){
			System.out.println(i+","+provider.getName()+","+provider.getVersion()+","+provider.getClass().getName());
			i++;
		}
	}
	
	@Test
	public void testAlgorithmParameters() throws NoSuchAlgorithmException, IOException{
		AlgorithmParameters ap=AlgorithmParameters.getInstance("DES");
		ap.init(new BigInteger("123123232").toByteArray());
		byte[] b=ap.getEncoded();
		System.out.println(new BigInteger(b).toString());
	}
	
	@Test
	public void testAlgorithmParameterGenerator() throws NoSuchAlgorithmException, IOException{
		AlgorithmParameterGenerator apg=AlgorithmParameterGenerator.getInstance("DES");
		apg.init(56);
		AlgorithmParameters ap=apg.generateParameters();
		byte[] b=ap.getEncoded();
		System.out.println(new BigInteger(b).toString());
	}
	
	@Test
	public void testKeyPairGenerator() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("DSA");
		keyPairGenerator.initialize(1024);
		KeyPair keyPair=keyPairGenerator.generateKeyPair();
		PrivateKey privateKey=keyPair.getPrivate();
		PublicKey publicKey=keyPair.getPublic();
		System.out.println(new String(privateKey.getEncoded()));
		System.out.println("------------------------------");
		System.out.println(new String(publicKey.getEncoded()));
	}
}
