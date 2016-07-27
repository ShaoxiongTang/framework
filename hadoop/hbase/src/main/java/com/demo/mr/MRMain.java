package com.demo.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapRunnable;
import org.apache.hadoop.mapred.OutputFormat;
import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.mapred.SequenceFileInputFormat;
import org.apache.hadoop.mapred.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.streaming.PipeMapper;

public class MRMain {
	
	InputFormat<String, String> oldInputFormat;
	org.apache.hadoop.mapreduce.InputFormat<String, String> newInputFormat;
	FileInputFormat<String, String> fileInputFormat;
	
	org.apache.hadoop.mapred.RecordReader<String, String> oldRecordReader;
	org.apache.hadoop.mapreduce.RecordReader<String, String> newRecordReader;
	
	SequenceFileInputFormat<String, String> oldSequenceFileInputFormat;
	
	OutputFormat<String, String> oldOutputFormat;
	FileOutputFormat<String, String> fileOutputFormat;
	
	org.apache.hadoop.mapreduce.OutputFormat<String, String> newOutputFormat;
	
	MapRunnable<String, String, String, String> mapRunnable;
	
	Partitioner<String, String> partitioner;
	org.apache.hadoop.mapreduce.Partitioner<String, String> newPartitioner;
	
	ProcessBuilder processBuilder;
	
	PipeMapper pipeMapper;
	
	JobControl jobControl;
	
	org.apache.hadoop.util.RunJar runJar;
	
	public static void main(String[] args){
		
	}
	
	public static void oldJob() throws IOException{
		JobConf jobConf = new JobConf(new Configuration(), MRMain.class);
		jobConf.setJobName("oldJob");
		jobConf.setMapperClass(OldMapper.class);
		jobConf.setReducerClass(OldReducer.class);
		
		JobClient.runJob(jobConf);
	}
	
	public static void newJob() throws IOException, ClassNotFoundException, InterruptedException{
		Job job = Job.getInstance(new Configuration(), "newJob");
		job.setJarByClass(MRMain.class);
		job.setMapperClass(NewMapper.class);
		job.setReducerClass(NewReducer.class);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
