package com.hhq.gateway.customer.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginVO {
    /**
     * 登陆类型：1-密码登陆；2-手机验证码登陆；3-邮箱登陆
     */
    @NotEmpty(message = "登陆类型不能为空")
    private String loginType;
    /**
     * 手机号，用户名，邮箱
     */
    @NotEmpty(message = "登录名不能为空")
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String vCode;
    /**
     * 业务id
     */
    private String bid;
}
