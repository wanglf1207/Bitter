package com.bitter.esb;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.bitter.handlers.ServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.bitter.handlers.SpringProtocolInitalizer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * @author wanglf
 * 配置类
 */
@Configuration
public class Config {
	
	@Value("${boss.thread.count}")
	private int bossCount;

	@Value("${worker.thread.count}")
	private int workerCount;

	@Value("${tcp.port}")
	private int tcpPort;

	@Value("${so.keepalive}")
	private boolean keepAlive;

	@Value("${so.backlog}")
	private int backlog;
	
	@Autowired
	@Qualifier("springProtocolInitializer")
	private SpringProtocolInitalizer protocolInitalizer;

	@SuppressWarnings("unchecked")
	@Bean(name = "serverBootstrap")
	public ServerBootstrap bootstrap() {
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup(), workerGroup())
				.channel(NioServerSocketChannel.class)
				.childHandler(protocolInitalizer
                        /*new ChannelInitializer<SocketChannel>() { // (4)
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerHandler());
                    }
                }*/);
		Map<ChannelOption<?>, Object> tcpChannelOptions = tcpChannelOptions();
		Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
		for (@SuppressWarnings("rawtypes")
		    ChannelOption option : keySet) {
			b.option(option, tcpChannelOptions.get(option));
		}
		return b;
	}

	@Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup bossGroup() {
		return new NioEventLoopGroup(bossCount);
	}

	@Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup workerGroup() {
		return new NioEventLoopGroup(workerCount);
	}

	@Bean(name = "tcpSocketAddress")
	public InetSocketAddress tcpPort() {
		return new InetSocketAddress(tcpPort);
	}

	@Bean(name = "tcpChannelOptions")
	public Map<ChannelOption<?>, Object> tcpChannelOptions() {
		Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
		options.put(ChannelOption.SO_KEEPALIVE, keepAlive);
		options.put(ChannelOption.SO_BACKLOG, backlog);// 设置TCP参数
		return options;
	}

	@Bean(name = "stringEncoder")
	public StringEncoder stringEncoder() {
		return new StringEncoder();
	}

	@Bean(name = "stringDecoder")
	public StringDecoder stringDecoder() {
		return new StringDecoder();
	}

	/**
	 * Necessary to make the Value annotations work.
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}	
}
