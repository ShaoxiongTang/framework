package com.demo.mr;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class OldReducer implements Reducer<String, String, String, String>{

	@Override
	public void configure(JobConf job) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reduce(String key, Iterator<String> values, OutputCollector<String, String> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

}
