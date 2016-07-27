package com.demo.mr;

import java.io.IOException;

import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class OldMapper implements Mapper<String, String, String, String>{

	@Override
	public void configure(JobConf job) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void map(String key, String value, OutputCollector<String, String> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	
}
