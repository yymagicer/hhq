package com.hhq.user.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.Table;

/**
 * 角色资源关系PO
 */
@Data
@Table(name = "t_role_resource_rel")
public class RoleResourceRelPO extends BasePO {

    /**
     * 关联id
     */
    private String relId;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 资源id
     */
    private String resourceId;
}
