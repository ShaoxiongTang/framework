package com.demo.bio.time.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

	public static void main(String[] args){
		ServerSocket serverSocket=null;
		int port=8080;
		try {
			serverSocket=new ServerSocket(port);
			System.out.println("the time server start in port :"+port);
			while(true){
				Socket socket=serverSocket.accept();
				new Thread(new TimeServerHandler(socket)).start();
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
