package com.demo.jndi.j2se;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.demo.jndi.j2se.service.DataSourceService;

public class JNDIContainer {

	private Context ctx=null;
	
	public void init() throws Exception{
		Hashtable<String,String> env = new Hashtable<String,String>();
	    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
	    env.put(Context.PROVIDER_URL, "file:/c:/sample");
	    ctx=new InitialContext(env);
	    loadServices();
	}
	
	private void loadServices() throws Exception {
		InputStream in=getClass().getClassLoader().getResourceAsStream("JNDIContainer.properties");
		Properties props=new Properties();
		props.load(in);
		  
		String dataSourceServiceName=props.getProperty("DataSourceServiceName");
		String dataSourceServiceClass=props.getProperty("DataSourceServiceClass");
		String dataSourceServiceUrl=props.getProperty("DataSourceServiceUrl");
		Object obj=Class.forName(dataSourceServiceClass).newInstance();
		if(obj instanceof DataSourceService){
			DataSourceService dataSource=(DataSourceService) obj;
			dataSource.setUrl(dataSourceServiceUrl);
			ctx.rebind(dataSourceServiceName,obj);
		}
	}

	public void close() throws NamingException{
		ctx.close();
	}
		 
	public Context getContext(){
		return ctx;
	}
}
