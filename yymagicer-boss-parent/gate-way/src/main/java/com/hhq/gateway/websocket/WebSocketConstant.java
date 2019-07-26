package com.hhq.gateway.websocket;

import com.hhq.common.constant.BaseConstant;

public class WebSocketConstant extends BaseConstant {

    /**
     * 发送的方式:1-点对点；
     */
    public static final String SEND_TYPE_ONE_TO_ONE = "1";
    /**
     * 发送的方式:2-点对多
     */
    public static final String SEND_TYPE_ONE_TO_MANY = "2";
    /**
     * 发送的方式:3-群发
     */
    public static final String SEND_TYPE_GROUP = "3";
    /**
     * redis websocket前缀
     */
    public static final String HHQ_SOCKET_PREFIX = "hhq:websocket:";

    /**
     执行方法：1-注册
     */
    public static final String EXEC_METHOD_LOGIN = "1";

    /**
     执行方法：2-发送消息
     */
    public static final String EXEC_METHOD_SEND_MSG = "2";

    /**
     执行方法：3-注销
     */
    public static final String EXEC_METHOD_LOGOUT = "3";
}
