package com.demo.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class FileChannelDemo {

	@Test
	public void readFile() throws IOException{
		RandomAccessFile file=new RandomAccessFile("D:\\test.txt","rw");
		FileChannel fileChannel=file.getChannel();
		ByteBuffer buf=ByteBuffer.allocate(6);
		
		int count=fileChannel.read(buf);
		while(count!=-1){
			System.out.println("read count :"+count);
			buf.flip();
			
			while(buf.hasRemaining()){
				System.out.println(buf.get());
			}
			
			buf.clear();
			count=fileChannel.read(buf);
		}
		fileChannel.close();
		file.close();
	}
}
