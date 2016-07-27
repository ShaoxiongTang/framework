package com.demo.hbase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.security.User;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;

public class SyslogHbaseTable {
	
	public static  void main(String[] args) throws Exception{
		String hYTableName="LGW-SYSLog-HY";
		if(args[5].equals("true")){
			if(args[0].equals("true")){
				testOldHbase(args[1],args[2], hYTableName);
			}
			if(args[3].equals("true")){
				testNewHbase(args[4], hYTableName);
			}
		}else{
			if(args[3].equals("true")){
				testNewHbase(args[4], hYTableName);
			}
			if(args[0].equals("true")){
				testOldHbase(args[1],args[2], hYTableName);
			}
		}
	}
	
	private static void testOldHbase2(String coreSite,String oldHbase,String hYTableName) throws Exception{
		Configuration oldConf =   HBaseConfiguration.create();  
		oldConf.addResource(oldHbase);  
		oldConf.set("hadoop.security.authentication","kerberos");
        UserGroupInformation.setConfiguration(oldConf);

        UserGroupInformation.loginUserFromKeytab("opsdev@DC.SH.CTRIPCORP.COM", "/home/op1/opsdev.keytab");
		
		byte[] row1=Bytes.toBytes("row1");
		byte[] family=Bytes.toBytes("s");
		byte[] qualifier=Bytes.toBytes("c1");
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
		byte[] value=Bytes.toBytes(format.format(new Date()));
		Put put=new Put(row1);
		put.add(family, qualifier, value);
		
		
		HTable oldhyTable=new HTable(oldConf, hYTableName);
		/*HConnection connection = HConnectionManager.createConnection(oldConf); 
		ExecutorService pool=Executors.newFixedThreadPool(3);
		HTable oldhyTable=new HTable(Bytes.toBytes(hYTableName), connection, pool);*/
		oldhyTable.put(put);
		oldhyTable.flushCommits();
		
		System.out.println("old hbase hadoop.kerberos.kinit.command:"+oldConf.get("hadoop.kerberos.kinit.command"));
		System.out.println("当前用户是："+User.getCurrent());
		
		Get get=new Get(row1);
		System.out.println("old table get "+oldhyTable.get(get));
		System.out.println("old over");
		oldhyTable.close();
		/*connection.close();
		pool.shutdown();*/
	}
	
	private static void testOldHbase(String coreSite,String oldHbase,String hYTableName) throws Exception{
		Configuration oldConf=new Configuration(false);
		oldConf.addResource(coreSite);
		oldConf.addResource(oldHbase);
		
		byte[] row1=Bytes.toBytes("row1");
		byte[] family=Bytes.toBytes("s");
		byte[] qualifier=Bytes.toBytes("c1");
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
		byte[] value=Bytes.toBytes(format.format(new Date()));
		Put put=new Put(row1);
		put.add(family, qualifier, value);
		
		System.out.println("老集群创建table之前----------------------");
		printCurrentUser();
		HTable oldhyTable=new HTable(oldConf, hYTableName);
		System.out.println("老集群创建table之后----------------------");
		printCurrentUser();
		/*HConnection connection = HConnectionManager.createConnection(oldConf); 
		ExecutorService pool=Executors.newFixedThreadPool(3);
		HTable oldhyTable=new HTable(Bytes.toBytes(hYTableName), connection, pool);*/
		oldhyTable.put(put);
		oldhyTable.flushCommits();
		
		System.out.println("old hbase hadoop.kerberos.kinit.command:"+oldConf.get("hadoop.kerberos.kinit.command"));
		System.out.println("老集群Put操作之后----------------------");
		printCurrentUser();
		
		Get get=new Get(row1);
		System.out.println("old table get "+oldhyTable.get(get));
		System.out.println("old over");
		oldhyTable.close();
		/*connection.close();
		pool.shutdown();*/
	}
	
	private static void testNewHbase(String newhbaseConfig,String hYTableName) throws Exception{
		Configuration newConf=new Configuration(false);
		newConf.addResource(newhbaseConfig);
		
		HBaseAdmin admin=new HBaseAdmin(newConf);
		
		HTableDescriptor[] tableNames=admin.listTables();
		System.out.println("list table names:");
		for(HTableDescriptor item:tableNames){
			System.out.println(item.getNameAsString());
		}
		
		System.out.println("new hbase hadoop.kerberos.kinit.command:"+newConf.get("hadoop.kerberos.kinit.command"));
		
		HTableDescriptor hYTable=new HTableDescriptor(hYTableName);
		HColumnDescriptor hcd=new HColumnDescriptor("s");
		hYTable.addFamily(hcd);
		
		byte[] row1=Bytes.toBytes("row1");
		byte[] family=Bytes.toBytes("s");
		byte[] qualifier=Bytes.toBytes("c1");
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
		byte[] value=Bytes.toBytes(format.format(new Date()));
		Put put=new Put(row1);
		put.add(family, qualifier, value);
		
		System.out.println("新集群创建table之前----------------------");
		printCurrentUser();
		HTable hyTable=new HTable(newConf, hYTableName);
		System.out.println("新集群创建table之后----------------------");
		printCurrentUser();
		/*HConnection connection = HConnectionManager.createConnection(newConf); 
		ExecutorService pool=Executors.newFixedThreadPool(3);
		HTable hyTable=new HTable(Bytes.toBytes(hYTableName), connection, pool);*/
		hyTable.put(put);
		hyTable.flushCommits();
		
		Scan scan=new Scan(row1);
		ResultScanner resultScanner=hyTable.getScanner(scan);
		Iterator<Result> iterator=resultScanner.iterator();
		System.out.println("datas list:");
		while(iterator.hasNext()){
			Result result=iterator.next();
			System.out.println(result);
		}
		System.out.println("新集群Put之后----------------------");
		printCurrentUser();
		System.out.println("over");
		resultScanner.close();
		hyTable.close();
		/*connection.close();
		pool.shutdown();*/
	}
	
	private static void printCurrentUser() throws Exception{
		System.out.println("-------------------------------------------------");
		User user=User.getCurrent();
		System.out.println("当前用户是："+user);
		UserGroupInformation ugi=user.getUGI();
		System.out.println("hasKerberosCredentials"+ugi.hasKerberosCredentials());
		System.out.println("isLoginKeytabBased"+ugi.isLoginKeytabBased());
		System.out.println("isSecurityEnabled"+ugi.isSecurityEnabled());
		System.out.println("-------------------------------------------------");
	}
}
