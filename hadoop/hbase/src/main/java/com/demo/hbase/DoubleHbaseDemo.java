package com.demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapreduce.Cluster;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.yarn.client.RMFailoverProxyProvider;

public class DoubleHbaseDemo {
	
	InputFormat<String, String> in;
	/*TableInputFormatBase base;
	TableInputFormat habseTable;
	TableMapper tableMapper;
	TableOutputFormat tableOutputFormat;
	HRegionServer hRegionServer;*/
	/*HRegion hRegion;
	HBaseServer hBaseServer;
	HBaseRPC hbaseRPC;
	MultiRowMutationEndpoint multiRowMutationEndpoint;*/
	OutputFormat<String, String> out;
	FileSystem fs;
	Cluster cluster;
	RMFailoverProxyProvider rmFailoverProxyProvider;
	JobClient jobClient;
	RunningJob runningJob;
	org.apache.hadoop.hbase.client.NoServerForRegionException ex;
	HTable hTable;

	public static void main(String[] args) throws Exception{
		
		String hYTableName="LGW-SYSLog-HY";
		Configuration oldConf=new Configuration(false);
		oldConf.addResource("core-site.xml");
		oldConf.addResource("oldhbase-site.xml");
		
		HTable oldhyTable=new HTable(oldConf, hYTableName);
		
		Configuration newConf=new Configuration(false);
		newConf.addResource("newhbase-site.xml");
		
		HTable newhyTable=new HTable(newConf, hYTableName);
		
		oldhyTable.close();
		newhyTable.close();
	}
}
