package com.hhq.gateway.user.vo;

import lombok.Data;

import java.util.List;

@Data
public class LoginRespVO {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String userRealName;
    /**
     * htk
     */
    private String htk;

    private String token;
}
