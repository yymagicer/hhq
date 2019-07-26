package com.hhq.user.service;

import com.hhq.common.base.BaseService;
import com.hhq.user.po.UserPO;

import java.util.List;

/**
 * 用户service
 */
public interface UserService extends BaseService<UserPO> {

    UserPO getByName(String userName);

    /**
     * 注册用户
     * @param user
     * @return
     */
    UserPO registUser(UserPO user);

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    UserPO queryByUserId(String userId);
}
