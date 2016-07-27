package com.demo.bio.time.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TimeClient {

	public static void main(String[] args){
		Socket socket=new Socket();
		BufferedReader in=null;
		PrintWriter out=null;
		try {
			socket.connect(new InetSocketAddress(8080));
			in=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			out=new PrintWriter(socket.getOutputStream(),true);
			
			String order="query time order";
			out.println(order);
			System.out.println("client send request:"+order);
			String res=in.readLine();
			System.out.println("client receive response:"+res);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				out.close();
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			in=null;
			out=null;
			socket=null;
		}
	}
}
