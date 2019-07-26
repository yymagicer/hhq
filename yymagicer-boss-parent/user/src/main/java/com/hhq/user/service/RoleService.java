package com.hhq.user.service;

import com.hhq.common.base.BaseService;
import com.hhq.user.po.RolePO;

public interface RoleService extends BaseService<RolePO> {

    RolePO queryByName(String roleName);

    RolePO queryByRoleId(String roleId);
}
