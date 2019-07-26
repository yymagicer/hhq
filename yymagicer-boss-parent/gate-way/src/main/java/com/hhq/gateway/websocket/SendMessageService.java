package com.hhq.gateway.websocket;

import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService {

    @Autowired
    private RedisTemplate redisTemplate;
    public  void beforeSend(SendMessage sendMessage){

    }
    /**
     * 发送消息
     * @param sendMessage
     */
    @Async
    public void sendMsg(SendMessage sendMessage){
        beforeSend(sendMessage);
        ChannelGroup channelGroup = null;
        //群发
        if(WebSocketConstant.SEND_TYPE_GROUP.equals(sendMessage.getSendType())){
            channelGroup = NettyConfig.getChannelGroup();
            if(null!=channelGroup){
                channelGroup.writeAndFlush(new TextWebSocketFrame(sendMessage.getContent()));
            }
        }else{
            ChannelId sender = (ChannelId) redisTemplate.opsForValue().get(WebSocketConstant.HHQ_SOCKET_PREFIX + sendMessage.getSender());
            channelGroup = NettyConfig.getChannelGroup();
            if(null!=channelGroup){
                channelGroup.find(sender).writeAndFlush(new TextWebSocketFrame(sendMessage.getContent()));
                //接受消息
                if(StringUtils.isNotEmpty(sendMessage.getReceivers())){
                    for(String receiver:sendMessage.getReceivers().split(",")){
                        ChannelId receiverId = (ChannelId) redisTemplate.opsForValue().get(WebSocketConstant.HHQ_SOCKET_PREFIX + receiver);
                        if(null!=receiverId){
                            channelGroup.find(receiverId).writeAndFlush(new TextWebSocketFrame(sendMessage.getContent()));
                        }
                    }
                }
            }
        }
    }

}
