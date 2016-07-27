package com.demo.nio.time.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class TimeServerHandler implements Runnable{

	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	
	
	public TimeServerHandler(Selector selector,ServerSocketChannel serverSocketChannel){
		this.selector=selector;
		this.serverSocketChannel=serverSocketChannel;
	}

	@Override
	public void run() {
		try {
			serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
			while(true){
				selector.select();
				Set<SelectionKey> selectionKeys=selector.selectedKeys();
				for(SelectionKey selectionKey:selectionKeys){
					if(selectionKey.isAcceptable()){
						SocketChannel socketChannel=serverSocketChannel.accept();
						socketChannel.configureBlocking(false);
						socketChannel.socket().setReuseAddress(true);
						selectionKey.attach(socketChannel);
						socketChannel.register(selector,SelectionKey.OP_READ);
					}else if(selectionKey.isReadable()){
						SocketChannel socketChannel=(SocketChannel)selectionKey.attachment();
						//socketChannel.read(dst);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(selector!=null){
				try {
					selector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(serverSocketChannel!=null){
				try {
					serverSocketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			selector=null;
			serverSocketChannel=null;
		}
	}
	
	
}
