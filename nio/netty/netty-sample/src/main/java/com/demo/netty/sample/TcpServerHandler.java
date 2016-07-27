package com.demo.netty.sample;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class TcpServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelInactive");
		super.channelInactive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		try {
			// 接收客户端的数据  
			ByteBuf byteBuff=(ByteBuf) msg;
			System.out.println("channelRead:" + byteBuff.toString(CharsetUtil.UTF_8));
			
			// 发送到客户端  
			byte[] responseByteArray = "你好".getBytes("UTF-8");  
			ByteBuf out = ctx.alloc().buffer(responseByteArray.length);  
			out.writeBytes(responseByteArray);  
			ctx.writeAndFlush(out);
		} catch (Exception e) {
			e.printStackTrace();
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		super.exceptionCaught(ctx, cause);
	}

	
}
