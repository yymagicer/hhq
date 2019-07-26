package com.hhq.gateway.websocket;

import lombok.Data;

import java.util.List;

/**
 * 发送消息
 */
@Data
public class SendMessage extends BusinessAction{

    /**
     *发送的方式:1-点对点；2-点对多；3-群发
     */
    private String sendType;
    /**
     * 发送者
     */
    private String sender;
    /**
     * 接受者
     */
    //private List<String> receivers;
    /**
     * 接受者
     */
    private String receivers;
    /**
     * channelId
     */
    private String channelId;
    /**
     * 群channelId
     */
    private String groupChannelId;
    /**
     * 消息内容
     */
    private String content;
}
