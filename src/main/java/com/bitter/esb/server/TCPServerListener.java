package com.bitter.esb.server;

import java.net.InetSocketAddress;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;

/**
 * @author wanglf
 */
@Component
public class TCPServerListener {

	@Autowired
	@Qualifier("serverBootstrap")
	private ServerBootstrap bootstrap;
	
	@Autowired
	@Qualifier("tcpSocketAddress")
	private InetSocketAddress tcpPort;

	private ChannelFuture serverChannelFuture;

	@PostConstruct
	public void start() throws Exception {
		System.out.println("Starting server at " + tcpPort);
		// 绑定本地端口等待客户端连接
		serverChannelFuture = bootstrap.bind(tcpPort).sync();
	}

	@PreDestroy
	public void stop() throws Exception {
        // 等待接受客户端连接的Channel被关闭
	    serverChannelFuture.channel().closeFuture().sync();
	}

	public ServerBootstrap getBootstrap() {
		return bootstrap;
	}

	public void setBootstrap(ServerBootstrap bootstrap) {
		this.bootstrap = bootstrap;
	}

	public InetSocketAddress getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(InetSocketAddress tcpPort) {
		this.tcpPort = tcpPort;
	}
}
