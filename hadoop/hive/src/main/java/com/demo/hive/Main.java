package com.demo.hive;

import java.io.UnsupportedEncodingException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.server.namenode.NameNode;
import org.apache.hadoop.hive.cli.CliDriver;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.HiveMetaStore.HMSHandler;
import org.apache.hadoop.hive.metastore.MetaStoreEventListener;
import org.apache.hadoop.hive.metastore.MetaStorePreEventListener;
import org.apache.hadoop.hive.metastore.ObjectStore;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.HiveDriverRunHook;
import org.apache.hadoop.hive.ql.exec.DDLTask;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.parse.AbstractSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.authorization.HiveAuthorizationTaskFactoryImpl;
import org.apache.hadoop.hive.ql.security.HadoopDefaultAuthenticator;
import org.apache.hadoop.hive.ql.security.HadoopDefaultMetastoreAuthenticator;
import org.apache.hadoop.hive.ql.security.SessionStateUserAuthenticator;
import org.apache.hadoop.hive.ql.security.authorization.AuthorizationPreEventListener;
import org.apache.hadoop.hive.ql.security.authorization.DefaultHiveAuthorizationProvider;
import org.apache.hadoop.hive.ql.security.authorization.DefaultHiveMetastoreAuthorizationProvider;
import org.apache.hadoop.hive.ql.security.authorization.HiveAuthorizationProvider;
import org.apache.hadoop.hive.ql.security.authorization.StorageBasedAuthorizationProvider;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveAuthorizer;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveAuthorizerFactory;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HivePrivilege;
import org.apache.hadoop.hive.thrift.HadoopThriftAuthBridge;
import org.apache.hadoop.security.Groups;
import org.apache.hive.service.cli.CLIService;
import org.apache.hive.service.cli.SessionHandle;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.hive.service.cli.session.HiveSessionHook;
import org.apache.hive.service.cli.session.SessionManager;
import org.apache.hive.service.cli.thrift.TCLIService;
import org.apache.hive.service.server.HiveServer2;
import org.apache.thrift.server.TServer;

public class Main {

	HiveMetaStore hiveMetaStore;
	HadoopThriftAuthBridge hadoopThriftAuthBridge;

	HiveServer2 hiveServer2;
	SessionManager sessionManager;
	HiveSession hiveSession;
	SessionHandle sessionHandler;
	TCLIService tCLIService;
	HiveConf hiveConf;
	CLIService cliService;
	

	HiveAuthorizationTaskFactoryImpl hiveAuthorizationTaskFactory;
	AbstractSemanticAnalyzerHook hook;
	HiveSemanticAnalyzerHook hiveSemanticAnalyzerHook;
	
	HiveAuthorizationProvider hiveAuthorizationProvider;
	Driver driver;
	CliDriver cliDriver;
	
	
	HiveAuthorizationTaskFactoryImpl hiveAuthorizationTaskFactoryImpl;
	
	HiveAuthorizer hiveAuthorizer;
	
	HiveAuthorizerFactory hiveAuthorizerFactory;
	
	HiveSessionHook hiveSessionHook;
	
	ObjectStore objectStore;
	
	SessionStateUserAuthenticator sessionStateUserAuthenticator;
	
	DefaultHiveAuthorizationProvider defaultHiveAuthorizationProvider;
	DefaultHiveMetastoreAuthorizationProvider defaultHiveMetastoreAuthorizationProvider;
	
	HadoopDefaultAuthenticator hadoopDefaultAuthenticator;
	
	HiveDriverRunHook hiveDriverRunHook;
	
	HadoopDefaultMetastoreAuthenticator hadoopDefaultMetastoreAuthenticator;
	AuthorizationPreEventListener authorizationPreEventListener;
	
	StorageBasedAuthorizationProvider  storageBasedAuthorizationProvider;
	
	HMSHandler handler;
	
	Hive hive;
	NullPointerException npe;
	Groups groups;
	
	//DefaultAuthorizationProvider defaultAuthorizationProvider;
	
	Configuration conf;
	
	NameNode nameNode;
	
	MetaStoreEventListener metaStoreEventListener;
	MetaStorePreEventListener metaStorePreEventListener;
	
	HivePrivilege hivePrivilege;
	DDLTask ddlTask;
	
	TServer tServer;
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		String[] parts = ":r-x:false".split(":");
		for(String item : parts){
			System.out.println(item);
		}
	}
}
                                                                                                        