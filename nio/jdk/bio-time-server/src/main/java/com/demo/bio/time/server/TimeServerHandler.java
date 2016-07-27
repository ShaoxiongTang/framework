package com.demo.bio.time.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable{
	
	private Socket socket;
	
	public TimeServerHandler(Socket socket) {
		this.socket=socket;
	}

	@Override
	public void run() {
		BufferedReader in=null;
		PrintWriter out=null;
		
		try {
			in=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			out=new PrintWriter(socket.getOutputStream(),true);
			
			String body=null;
			String currentTime=null;
			while(true){
				body=in.readLine();
				if(body==null){
					break;
				}
				System.out.println("the time server receive order : "+body);
				currentTime="query time order".equals(body)?new Date(System.currentTimeMillis()).toString():"bad order";
				out.println(currentTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(out!=null){
				out.close();
			}
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			in=null;
			out=null;
			socket=null;
		}
	}

}
