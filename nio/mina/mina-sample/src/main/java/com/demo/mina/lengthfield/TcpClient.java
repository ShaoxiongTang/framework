package com.demo.mina.lengthfield;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException{
		 	Socket socket = null;  
		 	DataOutputStream out = null;  
	        InputStream in = null;  
	          
	        try{  
	              
	            socket = new Socket("localhost", 8080);        
	            out = new DataOutputStream(socket.getOutputStream());   
	            in = socket.getInputStream();  
	              
	         // 请求服务器  
	            String data1 = "牛顿";  
	            byte[] outputBytes1 = data1.getBytes("UTF-8");  
	            out.writeInt(outputBytes1.length); // write header  
	            out.write(outputBytes1); // write body  
	              
	            String data2 = "爱因斯坦";  
	            byte[] outputBytes2 = data2.getBytes("UTF-8");  
	            out.writeInt(outputBytes2.length); // write header  
	            out.write(outputBytes2); // write body  
	              
	            out.flush();  
	              
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
