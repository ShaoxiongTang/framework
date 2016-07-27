package com.demo.netty.sample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException{
		 	Socket socket = null;  
	        OutputStream out = null;  
	        InputStream in = null;  
	          
	        try{  
	              
	            socket = new Socket("localhost", 8080);        
	            out = socket.getOutputStream();  
	            in = socket.getInputStream();  
	              
	            // 请求服务器  
	            out.write("第一次请求".getBytes("UTF-8"));  
	            out.flush();  
	                      
	            // 获取服务器响应，输出  
	            byte[] byteArray = new byte[1024];  
	            int length = in.read(byteArray);  
	            System.out.println(new String(byteArray, 0, length, "UTF-8"));  
	              
	            Thread.sleep(5000);  
	              
	            // 再次请求服务器  
	            out.write("第二次请求".getBytes("UTF-8"));  
	            out.flush();  
	              
	            // 再次获取服务器响应，输出  
	            byteArray = new byte[1024];  
	            length = in.read(byteArray);  
	            System.out.println(new String(byteArray, 0, length, "UTF-8"));  
	              
	              
	        } finally {  
	            // 关闭连接  
	        	if(in!=null){
	        		in.close(); 
	        	}
	        	if(out!=null){
	        		out.close(); 
	        	}
	        	if(socket!=null){
	        		socket.close(); 
	        	}
	        }  
	          
	}
}
