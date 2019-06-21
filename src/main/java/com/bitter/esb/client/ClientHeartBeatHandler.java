package com.bitter.esb.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ClientHeartBeatHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleState state = ((IdleStateEvent) evt).state();
			if (state == IdleState.WRITER_IDLE) {
				HeartBeatMessage msg = new HeartBeatMessage();
				msg.setHeartbeat("HB");
				ctx.writeAndFlush(msg);
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}

}
