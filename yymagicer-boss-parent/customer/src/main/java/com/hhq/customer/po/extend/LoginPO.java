package com.hhq.customer.po.extend;

import lombok.Data;

/**
 * 登陆
 */
@Data
public class LoginPO {

    /**
     * 登陆类型：1-密码登陆；2-验证码登陆；3-邮箱登陆
     */
    private String loginType;
    /**
     * 手机号，用户名，邮箱
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String vCode;
}
