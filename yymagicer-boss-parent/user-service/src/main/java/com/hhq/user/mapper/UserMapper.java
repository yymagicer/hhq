package com.hhq.user.mapper;

import com.hhq.common.base.BaseMapper;
import com.hhq.user.po.UserPO;

import java.util.List;

public interface UserMapper extends BaseMapper<UserPO> {

    List<UserPO> getList();
}
