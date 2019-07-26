package com.hhq.user.mapper;

import com.hhq.common.base.BaseMapper;
import com.hhq.user.po.UserPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<UserPO> {

    List<UserPO> getList();

    UserPO getByName(String userName);
}
