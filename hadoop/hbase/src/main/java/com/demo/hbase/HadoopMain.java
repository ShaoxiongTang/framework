package com.demo.hbase;

import org.apache.hadoop.hdfs.server.namenode.NameNode;
import org.apache.hadoop.hdfs.server.namenode.SecondaryNameNode;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SetFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.serializer.Serialization;
import org.apache.hadoop.mapred.MapTaskAttemptImpl;
import org.apache.hadoop.mapred.Task;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.v2.app.job.TaskAttempt;

public class HadoopMain {

	Writable writable;
	IntWritable intWritable;
	ObjectWritable objectWritable;
	Serialization<String> serialization;
	SequenceFile sequenceFile;
	MapFile mapFile;
	SetFile setFile;
	
	Task task;
	TaskAttempt taskAttempt;
	MapTaskAttemptImpl mapTaskAttemptImpl;
	
	NameNode nameNode;
	SecondaryNameNode secondaryNameNode;
	
	InputFormat<String, String> inputFormat;
	Mapper<String, String, String, String> mapper;
	Partitioner<String, String> partitioner;
	Reducer<String, String, String, String> reducer;
	OutputFormat<String, String> outputFormat;
	
	
	
}
