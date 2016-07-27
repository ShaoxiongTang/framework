package com.demo.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.proto.ClientNamenodeProtocolProtos.ClientNamenodeProtocol;
import org.apache.hadoop.hdfs.server.namenode.NameNode;
import org.apache.hadoop.ipc.RPC.Server;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.security.UserGroupInformation;

public class HiveJdbcTest {

	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	
	NameNode namenode;
	
	Server processConnectionContext;
	
	//HttpFSFileSystem.Operation ssd;
	FsAction fsAction;
	
	ClientNamenodeProtocol clientNamenodeProtocol;
	
	DistributedFileSystem afsf;

	Server server;
	public static void main(String[] args)throws Exception {
		
		UserGroupInformation.createProxyUser("lg",UserGroupInformation.getLoginUser());
		Job job;
		Configuration conf;
		try {
		    Class.forName(driverName);
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    System.exit(1);
		}
		
		//UserGroupInformation.createProxyUser(user, realUser);
		
		//Connection con = DriverManager.getConnection("jdbc:hive2://192.168.126.131:9083/default", "lg", "");
		Connection con = DriverManager.getConnection("jdbc:hive2://10.142.78.40:10000", "admin", "");
		String tableName = "persontest";
		/*Connection con = DriverManager.getConnection(
		                    "jdbc:hive2://192.168.126.131:10000/lgtest.db", "lg", "");*/
		Statement stmt = con.createStatement();
		/*stmt.execute("drop table if exists " + tableName);
		stmt.execute("create table " + tableName + 
		                              " (key int, value string)");
		System.out.println("Create table success!");*/
		// show tables
		String sql = "show tables '" + tableName + "'";
		System.out.println("Running: " + sql);
		ResultSet res = stmt.executeQuery(sql);
		if (res.next()) {
		    System.out.println(res.getString(1));
		}
		
		// describe table
		sql = "describe " + tableName;
		System.out.println("Running: " + sql);
		res = stmt.executeQuery(sql);
		while (res.next()) {
		    System.out.println(res.getString(1) + "\t" + res.getString(2));
		}
		
		sql = "select * from " + tableName;
		res = stmt.executeQuery(sql);
		while (res.next()) {
		    System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
		}
		
		sql = "select count(1) from " + tableName;
		System.out.println("Running: " + sql);
		res = stmt.executeQuery(sql);
		while (res.next()) {
		    System.out.println(res.getString(1));
		}
		System.out.println("over !");
	}
}
