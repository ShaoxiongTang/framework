package com.demo.nio;

import java.io.InputStream;
import java.nio.Buffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class Main {

	Channel channel;
	FileChannel fileChannel;
	SocketChannel socketChannel;
	ServerSocketChannel serverSocketChannel;
	Buffer buffer;
	Selector selector;
	
	SelectorProvider selectorProvider;
	
	InputStream in;
	
}
