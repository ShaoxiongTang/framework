package com.demo.nio.time.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class TimeServer {

	public static void main(String[] args){
		ServerSocketChannel serverSocketChannel=null;
		int port=8080;
		try {
			serverSocketChannel=ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(port));
			serverSocketChannel.configureBlocking(false);
			
			Selector selector=Selector.open();
			
			new Thread(new TimeServerHandler(selector,serverSocketChannel)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(serverSocketChannel!=null){
				try {
					System.out.println("the time server close");
					serverSocketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
