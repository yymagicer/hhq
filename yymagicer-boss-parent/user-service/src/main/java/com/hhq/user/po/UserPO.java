package com.hhq.user.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name="t_user")
public class UserPO extends BasePO {
    private Integer id;
    private String userName;
    private String userRealName;
    private String password;
    private String sex;
    private String mobile;
    private String email;
    private String headImage;
}
