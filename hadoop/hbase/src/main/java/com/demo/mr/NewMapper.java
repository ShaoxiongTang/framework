package com.demo.mr;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Mapper;

public class NewMapper extends Mapper<String, String, String, String>{

	@Override
	protected void map(String key, String value, Mapper<String, String, String, String>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.map(key, value, context);
	}

	
}
