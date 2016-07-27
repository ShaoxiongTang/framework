package com.demo.mr;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;

public class NewReducer extends Reducer<String, String, String, String>{

	@Override
	protected void reduce(String arg0, Iterable<String> arg1, Reducer<String, String, String, String>.Context arg2)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.reduce(arg0, arg1, arg2);
	}

	
}
