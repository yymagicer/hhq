package com.hhq.gateway.user.vo;

import lombok.Data;
import java.util.List;

@Data
public class UserInfoRespVO {
    private Integer id;
    private String userName;
    private String userRealName;
    private String password;
    private String sex;
    private String mobile;
    private String email;
    private String headImage;
    private String salt;
    /**
     * 角色
     */
    private List<RoleVO>  roles;
}
