package com.lg.shiro;


import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;
import java.util.Collection;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm.SaltStyle;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroTest {
	
	Realm asa;
	AesCipherService  sdf;
	Key sdcs;
	AlgorithmParameters sdsd;
	AlgorithmParameterGenerator sdfsfd;
	KeyPair xdsvcfd;
	KeyPairGenerator dsgf;
	KeyFactory sdfsdf;
	Signature sdfdsfd;
	SignedObject sdfdsfdgdf;
	 

	@Test  
	public void testHelloworld() {  
		init();
		
		register("wangwu","123");
		
		Subject subject=login("wangwu","123");
		System.out.println(subject.hasRole("role1"));
		System.out.println(subject.hasRole("role2"));
		System.out.println(subject.hasRole("role3"));
	}
	
	public void register(String username,String password){
		JdbcRealm jdbcRealm=getJdbcRelam();
		PasswordMatcher passwordMatcher=(PasswordMatcher) jdbcRealm.getCredentialsMatcher();
		String encryptPassword=passwordMatcher.getPasswordService().encryptPassword(password);
		//保存用户名和密文到数据库，这里不再做
		System.out.println(encryptPassword);
	}
	
	private Subject login(String userName,String password){
		 //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken(userName,password);  
	    subject.login(token);
	    return subject;
	}
	
	private void init(){
		 //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
	    Factory<org.apache.shiro.mgt.SecurityManager> factory =  
	            new IniSecurityManagerFactory("classpath:shiro.ini");  
	    //2、得到SecurityManager实例 并绑定给SecurityUtils  
	    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);  
        JdbcRealm jdbcRealm=getJdbcRelam();
        jdbcRealm.setSaltStyle(SaltStyle.COLUMN);
	}
	
	public JdbcRealm getJdbcRelam(){
		Collection<Realm> realms=((RealmSecurityManager)SecurityUtils.getSecurityManager()).getRealms();    
        return (JdbcRealm)realms.toArray()[0];
	}
}
