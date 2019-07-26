package com.hhq.user.service;

import com.hhq.common.base.BaseService;
import com.hhq.user.po.UserRoleRelPO;

import java.util.List;

public interface UserRoleRelService extends BaseService<UserRoleRelPO> {
    /**
     * 设置用户角色
     * @param userId
     * @param roles
     */
    void setUserRoles(String userId, List<String> roles);
}
