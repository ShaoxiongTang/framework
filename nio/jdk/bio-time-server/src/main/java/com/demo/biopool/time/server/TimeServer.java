package com.demo.biopool.time.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.demo.bio.time.server.TimeServerHandler;

public class TimeServer {
	
	public static void main(String[] args){
		ServerSocket serverSocket=null;
		int port=8080;
		try {
			serverSocket=new ServerSocket(port);
			System.out.println("the time server start in port :"+port);
			ExecutorService executorService=Executors.newFixedThreadPool(10);
			while(true){
				Socket socket=serverSocket.accept();
				executorService.submit(new TimeServerHandler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(serverSocket!=null){
				try {
					System.out.println("the time server close");
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
