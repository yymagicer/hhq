package com.hhq.user.service.impl;

import com.hhq.common.base.AbstractService;
import com.hhq.common.constant.UserConstant;
import com.hhq.common.util.IdWorkerUtil;
import com.hhq.user.mapper.UserRoleRelMapper;
import com.hhq.user.po.UserRoleRelPO;
import com.hhq.user.service.UserRoleRelService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserRoleServiceImpl extends AbstractService<UserRoleRelPO> implements UserRoleRelService {

    @Autowired
    private UserRoleRelMapper userRoleRelMapper;

    @Override
    public int save(UserRoleRelPO userRoleRelPO) {
        userRoleRelPO.setRelId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
        userRoleRelPO.setIsDelete(UserConstant.IS_NOTE_DELETE);
        userRoleRelPO.setCreateTime(new Date());
        return userRoleRelMapper.insertSelective(userRoleRelPO);
    }

    @Override
    public UserRoleRelPO get(UserRoleRelPO userRoleRelPO) {
        return userRoleRelMapper.selectOne(userRoleRelPO);
    }

    @Override
    public int update(UserRoleRelPO userRoleRelPO) {
        userRoleRelPO.setUpdateTime(new Date());
        return userRoleRelMapper.updateByPrimaryKeySelective(userRoleRelPO);
    }

    @Override
    public int delete(UserRoleRelPO userRoleRelPO) {
        userRoleRelPO.setIsDelete(UserConstant.IS_DELETE);
        userRoleRelPO.setUpdateTime(new Date());
        return userRoleRelMapper.updateByPrimaryKeySelective(userRoleRelPO);
    }

    @Override
    public List<UserRoleRelPO> queryList(UserRoleRelPO userRoleRelPO) {
        Example example = new Example(UserRoleRelPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDelete",UserConstant.IS_NOTE_DELETE);
        if(StringUtils.isNotEmpty(userRoleRelPO.getRoleId())){
            criteria.andEqualTo("roleId",userRoleRelPO.getRoleId());
        }
        if(StringUtils.isNotEmpty(userRoleRelPO.getUserId())){
            criteria.andEqualTo("userId",userRoleRelPO.getUserId());
        }
        example.setOrderByClause("create_time desc");
        return userRoleRelMapper.selectByExample(example);
    }

    @Override
    public int batchDelete(List<String> ids, String userId, String userName) {
        return 0;
    }

    @Override
    @Transactional
    public void setUserRoles(String userId, List<String> roles) {
        List<String> roleIds = userRoleRelMapper.queryRoleIds(userId);
        //待删除的角色id
        List<String> deleteRoleIds = roleIds;
        deleteRoleIds.removeAll(roles);
        //新增的角色id
        roles.removeAll(roleIds);
        List<String> insertRoleIds = roles;
        Example example = new Example(UserRoleRelPO.class);
        Example.Criteria criteria = example.createCriteria();
        //删除角色id
        if(CollectionUtils.isNotEmpty(deleteRoleIds)){
            UserRoleRelPO rel = new UserRoleRelPO();
            rel.setIsDelete(UserConstant.IS_DELETE);
            criteria.andIn("roleId",deleteRoleIds);
            userRoleRelMapper.updateByExample(rel,example);
        }
        // 新增角色id
        if(CollectionUtils.isNotEmpty(insertRoleIds)){
            List<UserRoleRelPO> userRoleRelPOList = new ArrayList<>();
            insertRoleIds.forEach(item->{
                UserRoleRelPO rel = new UserRoleRelPO();
                rel.setRelId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
                rel.setUserId(userId);
                rel.setRoleId(item);
                rel.setIsDelete(UserConstant.IS_NOTE_DELETE);
                rel.setCreateTime(new Date());
                userRoleRelPOList.add(rel);
            });
            userRoleRelMapper.insertList(userRoleRelPOList);
        }
    }
}
