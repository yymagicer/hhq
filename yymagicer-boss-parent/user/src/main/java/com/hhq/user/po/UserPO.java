package com.hhq.user.po;

import com.fasterxml.jackson.annotation.JsonView;
import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Table(name="t_user")
public class UserPO extends BasePO {
    private String userId;
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
    @Transient
    private List<String> roles;
}
