package com.lg.shiro;

import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.format.HashFormat;
import org.apache.shiro.crypto.hash.format.Shiro1CryptFormat;
import org.apache.shiro.util.ByteSource;

public class MyPasswordService implements PasswordService{
	
	private String algorithmName="MD5";
	private int iterations=5;
	private String salt="2014";
	
	private HashFormat hashFormat=new Shiro1CryptFormat();

	@Override
	public String encryptPassword(Object plaintextPassword)
			throws IllegalArgumentException {
		Hash hash=new SimpleHash(algorithmName,ByteSource.Util.bytes(plaintextPassword),ByteSource.Util.bytes(salt), iterations);
		return hashFormat.format(hash);
	}

	@Override
	public boolean passwordsMatch(Object submittedPlaintext, String encrypted) {
		Hash hash=new SimpleHash(algorithmName,ByteSource.Util.bytes(submittedPlaintext),ByteSource.Util.bytes(salt), iterations);
		String password=hashFormat.format(hash);
		return encrypted.equals(password);
	}
}
