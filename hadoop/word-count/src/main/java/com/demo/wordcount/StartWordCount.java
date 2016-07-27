package com.demo.wordcount;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class StartWordCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		conf.addResource("core-site.xml");
		conf.addResource("hdfs-site.xml");
        String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
        if(otherArgs.length != 2){
            System.err.println("Usage:wordcount <in> <out>");
            System.out.println("current args are:");
            for(String arg:args){
            	System.out.println(arg);
            }
            System.exit(2);  
        }
        
        ////设置Job属性  
        Job job = Job.getInstance(conf, "WordCount");
        job.setJarByClass(StartWordCount.class);  
        job.setMapperClass(WordCountMapper.class);  
        job.setCombinerClass(WordCountReduce.class);//将结果进行局部合并  
        job.setReducerClass(WordCountReduce.class);  
        job.setOutputKeyClass(Text.class);  
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));//传入input path  
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));//传入output path，输出路径应该为空，否则报错org.apache.hadoop.mapred.FileAlreadyExistsException。  
          
        System.exit(job.waitForCompletion(true)?0:1);//是否正常退出  
	}

}
