package com.hhq.user.service.impl;

import com.hhq.user.mapper.UserMapper;
import com.hhq.user.po.UserPO;
import com.hhq.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户service实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public int save(UserPO userPO) {
        return userMapper.insertSelective(userPO);
    }

    @Override
    public UserPO get(UserPO userPO) {
        return userMapper.selectOne(userPO);
    }

    @Override
    public int update(UserPO userPO) {
        return userMapper.updateByPrimaryKeySelective(userPO);
    }

    @Override
    public int delete(UserPO userPO) {
        return userMapper.updateByPrimaryKey(userPO);
    }

    @Override
    public List<UserPO> queryList(UserPO userPO) {
        return userMapper.select(userPO);
    }
    @Override
    public List<UserPO> getList() {
        return userMapper.getList();
    }
}
