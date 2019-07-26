package com.hhq.user.service.impl;
import com.hhq.common.base.AbstractService;
import com.hhq.common.constant.UserConstant;
import com.hhq.user.mapper.UserMapper;
import com.hhq.user.po.UserPO;
import com.hhq.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 用户service实现类
 */
@Service
public class UserServiceImpl extends AbstractService<UserPO> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
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
        Example example = new Example(UserPO.class);
        Example.Criteria criteria = example.createCriteria();
        if(null==userPO){
            userPO = new UserPO();
        }
        if(StringUtils.isNotEmpty(userPO.getUserName())){
            criteria.andEqualTo("userName",userPO.getUserName());
        }
        criteria.andEqualTo("isDelete", UserConstant.IS_NOTE_DELETE);
        example.setOrderByClause("user_name desc");
        return userMapper.selectByExample(example);
    }
    @Override
    public UserPO registUser(UserPO user) {
        userMapper.insertSelective(user);
        return user;
    }

    @Override
    public UserPO getByName(String userName) {
        UserPO user = new UserPO();
        user.setUserName(userName);
        return userMapper.selectOne(user);
    }

    @Override
    public int batchDelete(List<String> ids, String userId, String userName) {
        return 0;
    }

    @Override
    public UserPO queryByUserId(String userId) {
        UserPO user = new UserPO();
        user.setUserId(userId);
        return userMapper.selectOne(user);
    }
}
