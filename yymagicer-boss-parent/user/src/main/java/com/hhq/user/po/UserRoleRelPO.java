package com.hhq.user.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "t_user_role_rel")
public class UserRoleRelPO extends BasePO {
    /**
     * 关联id
     */
    private String relId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;

}
