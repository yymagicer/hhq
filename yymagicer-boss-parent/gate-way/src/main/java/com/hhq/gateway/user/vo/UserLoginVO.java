package com.hhq.gateway.user.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserLoginVO implements Serializable {
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 登陆ip
     */
    private String loginIp;
}
