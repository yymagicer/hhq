package com.hhq.gateway.customer.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class CustomerVO implements Serializable {
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 设备类型：1-小程序；2-安卓；3-ios；4-pc
     */
    @NotEmpty(message = "设备类型不能为空")
    private String deviceType;
    /**
     * 注册类型：1-手机号；2-用户名；3-电子邮箱
     */
    @NotEmpty(message = "注册类型不能为空")
    private String registType;
}
