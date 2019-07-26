package com.hhq.user.service;

import com.hhq.common.base.BaseService;
import com.hhq.user.po.UserPO;

import java.util.List;

/**
 * 用户service
 */
public interface UserService extends BaseService<UserPO> {
    List<UserPO> getList();
}
