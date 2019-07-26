package com.hhq.user.service.impl;

import com.hhq.common.base.AbstractService;
import com.hhq.common.constant.UserConstant;
import com.hhq.common.util.IdWorkerUtil;
import com.hhq.user.mapper.RoleMapper;
import com.hhq.user.po.RolePO;
import com.hhq.user.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceServiceImpl extends AbstractService<RolePO> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RolePO queryByName(String roleName) {
        RolePO rolePO = new RolePO();
        rolePO.setIsDelete(UserConstant.IS_NOTE_DELETE);
        rolePO.setRoleName(roleName);
        return roleMapper.selectOne(rolePO);
    }

    @Override
    public RolePO queryByRoleId(String roleId) {
        RolePO rolePO = new RolePO();
        rolePO.setRoleId(roleId);
        return roleMapper.selectOne(rolePO);
    }

    @Override
    public int save(RolePO rolePO) {
        rolePO.setRoleId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
        rolePO.setIsDelete(UserConstant.IS_NOTE_DELETE);
        rolePO.setCreateTime(new Date());
        return roleMapper.insertSelective(rolePO);
    }
    @Override
    public RolePO get(RolePO rolePO) {
        return roleMapper.selectOne(rolePO);
    }

    @Override
    public int update(RolePO rolePO) {
        rolePO.setUpdateTime(new Date());
        return roleMapper.updateByPrimaryKeySelective(rolePO);
    }

    @Override
    public int delete(RolePO rolePO) {
        rolePO.setIsDelete(UserConstant.IS_DELETE);
        rolePO.setUpdateTime(new Date());
        return roleMapper.updateByPrimaryKeySelective(rolePO);
    }
    @Override
    public List<RolePO> queryList(RolePO rolePO) {
        Example example = new Example(RolePO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDelete",UserConstant.IS_NOTE_DELETE);
        if(StringUtils.isNotEmpty(rolePO.getRoleName())){
            criteria.andLike("roleName",rolePO.getRoleName());
        }
        if(StringUtils.isNotEmpty(rolePO.getParentRoleId())){
            criteria.andEqualTo("parentRoleId",rolePO.getParentRoleId());
        }
        example.setOrderByClause("create_time desc");
        return roleMapper.selectByExample(example);
    }
    @Override
    public int batchDelete(List<String> ids, String userId, String userName) {
        return 0;
    }
}
