package com.hhq.gateway.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户VO
 */
@Data
public class UserVO implements Serializable {
    private Integer id;
    private String userName;
    private String userRealName;
    private String password;
    private String sex;
    private String mobile;
    private String email;
    private String headImage;
}
