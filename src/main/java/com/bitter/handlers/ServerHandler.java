package com.bitter.handlers;

import java.net.InetAddress;

import com.bitter.esb.model.demo.UserRequest;
import com.bitter.esb.service.ESBRouteService;
import com.bitter.utils.JAXBUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author wanglf
 */
@Component
@Qualifier("serverHandler")
@Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    @Autowired
    private ESBRouteService routeService;

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception  {
       //((ByteBuf) msg).release();
	    try {
           /* ByteBuf in = (ByteBuf) msg;
            byte[] req = new byte[in.readableBytes()];
            String body = new String(req, "UTF-8");*/
            System.out.println(ctx.channel().remoteAddress()+"->Server :"+ msg);

            logger.info("client msg:"+msg);
            String clientIdToLong= ctx.channel().id().asLongText();
            String message = msg.toString();

            // 返回给ESB的报文
            String returnMessage = doService(message);

            logger.info("client long id:"+clientIdToLong);
            String clientIdToShort= ctx.channel().id().asShortText();

            logger.info("client short id:"+clientIdToShort);
            // 去掉XML头中的"standalone="yes”属性
            returnMessage = returnMessage.replace("standalone=\"yes\"","");
            //send to client
            // Flus后不需要 ReferenceCountUtil.release(msg);
            ctx.channel().writeAndFlush("Yoru msg is:" + returnMessage);
            //close
            //ctx.channel().close();
        } finally {
	        // 还没搞清楚是不是一定要释放
          //  ReferenceCountUtil.release(msg);
        }
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    private String doService(String message) {


	    // 路由分发
        String retData = routeService.exec(message);
       /* try{
            logger.info("返回结果为[{}]" ,new String(retByte,"UTF8"));
        }catch(Exception e){
            logger.error("不支持UTF8编码",e);
        }*/

        return retData;
    }
	
}