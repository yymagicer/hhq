/**************************************************************************************** 
           南京小视科技有限公司           
 ****************************************************************************************/
package com.hhq.gateway.websocket;

import java.util.concurrent.TimeUnit;
import com.hhq.gateway.util.SpringContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Component;

/**
 * <Description> <br>
 * 
 * @author yangxiaodong<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2019年7月22日 <br>
 */

@Component
public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private RedisTemplate redisTemplate = (RedisTemplate) SpringContextHelper.getBean("redisTemplate");

    @Autowired
    private SendMessageService sendMessageService = (SendMessageService) SpringContextHelper.getBean("sendMessageService");
    /**
     * channel被启用的时候触发（在建立连接的时候）,服务端监听到客户端活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端建立连接，通道开启！");
        NettyConfig.getChannelGroup().add(ctx.channel());
    }
    /**
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端断开连接，通道关闭！");
        NettyConfig.getChannelGroup().remove(ctx.channel());
        // 添加到channelGroup 通道组
    }

    // 服务端接收客户端发送过来的数据结束之后调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    // 工程出现异常的时候调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 每当从服务端读到客户端写入信息时
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务器收到客户端的数据：" + msg.text());
        SendMessage sendMessage = JSONObject.parseObject(msg.text(), SendMessage.class);
        if(WebSocketConstant.EXEC_METHOD_LOGIN.equals(sendMessage.getExecMethod())){
            login(sendMessage.getUserId(),ctx);
        }else if(WebSocketConstant.EXEC_METHOD_SEND_MSG.equals(sendMessage.getExecMethod())){
            sendMsg(sendMessage);
        }
    }
    // ping、pong
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 用于触发用户事件，包含触发读空闲、写空闲、读写空闲
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.ALL_IDLE) {
                Channel channel = ctx.channel();
                // 关闭无用channel，以防资源浪费
                channel.close();
            }
        }
    }
    private void sendMsg(SendMessage sendMessage){
        sendMessageService.sendMsg(sendMessage);
    }
    private void login(String userId, ChannelHandlerContext ctx) {
        //这边可以查询用户的信息，获取用户所有的列表，群信息，然后，加载到map中，实现点对点，群发等功能
        redisTemplate.opsForValue().set(WebSocketConstant.HHQ_SOCKET_PREFIX + userId, ctx.channel().id(), 1, TimeUnit.DAYS);
    }
}