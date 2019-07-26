package com.hhq.gateway.shiro;

import com.hhq.gateway.customer.vo.LoginVO;
import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义token
 */
@Data
public class CustomUsernamePasswordToken extends UsernamePasswordToken {
    /**
     * 用户类型：1-客户端用户；2-后台用户
     */
    private String userType;

    private LoginVO loginVO;
    public CustomUsernamePasswordToken(String userName, String password,String userType){
        this.userType = userType;
        super.setUsername(userName);
        super.setPassword(password != null ? password.toCharArray() : null);
    }
    public CustomUsernamePasswordToken(String userName, String password,String userType,LoginVO loginVO){
        this.userType = userType;
        this.loginVO =loginVO;
        super.setUsername(userName);
        super.setPassword(password != null ? password.toCharArray() : null);
    }
}
