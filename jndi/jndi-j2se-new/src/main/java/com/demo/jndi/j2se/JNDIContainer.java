package com.demo.jndi.j2se;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

public class JNDIContainer {

	private Context ctx=null;
	
	public JNDIContainer(){
		Hashtable<String,String> env = new Hashtable<String,String>();
	    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
	    env.put(Context.PROVIDER_URL, "file:/c:/sample");
	    try {
			ctx=new InitialContext(env);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void init() throws Exception{
	    loadServices();
	}
	
	private void loadServices() throws Exception {
		InputStream in=getClass().getClassLoader().getResourceAsStream("JNDIContainer.properties");
		Properties props=new Properties();
		props.load(in);
		  
		String dataSourceServiceBindingName=props.getProperty("DataSourceServiceBindingName");
		String dataSourceServiceName=props.getProperty("DataSourceServiceName");
		String dataSourceServiceFactory=props.getProperty("DataSourceServiceFactory");
		String dataSourceServiceUrl=props.getProperty("DataSourceServiceUrl");
		
		Reference ref=new Reference(dataSourceServiceName,dataSourceServiceFactory,null);
		ref.add(new StringRefAddr("url",dataSourceServiceUrl));
		ctx.rebind(dataSourceServiceBindingName,ref);
	}

	public void close() throws NamingException{
		ctx.close();
	}
		 
	public Context getContext(){
		return ctx;
	}
}
