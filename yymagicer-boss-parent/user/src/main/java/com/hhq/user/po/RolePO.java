package com.hhq.user.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name="t_role")
public class RolePO extends BasePO {
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色编号
     */
    private String roleCode;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
    /**
     * 父类id
     */
    private String parentRoleId;
}
