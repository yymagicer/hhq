package com.hhq.gateway.websocket;

import lombok.Data;

@Data
public class BusinessAction {

    /**
     执行方法：1-注册；2-发送消息；3-注销
     */
    private String execMethod;
    /**
     * 用户id
     */
    private String userId;
}
