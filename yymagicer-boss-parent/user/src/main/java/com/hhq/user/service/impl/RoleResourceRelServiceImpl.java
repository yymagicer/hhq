package com.hhq.user.service.impl;

import com.hhq.common.base.AbstractService;
import com.hhq.common.constant.UserConstant;
import com.hhq.common.util.IdWorkerUtil;
import com.hhq.user.mapper.RoleResourceRelMapper;
import com.hhq.user.po.RoleResourceRelPO;
import com.hhq.user.service.RoleResourceRelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class RoleResourceRelServiceImpl extends AbstractService<RoleResourceRelPO> implements RoleResourceRelService {

    @Autowired
    private RoleResourceRelMapper roleResourceRelMapper;

    @Override
    public int save(RoleResourceRelPO roleResourceRelPO) {
        roleResourceRelPO.setRelId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
        roleResourceRelPO.setIsDelete(UserConstant.IS_NOTE_DELETE);
        roleResourceRelPO.setCreateTime(new Date());
        return roleResourceRelMapper.insertSelective(roleResourceRelPO);
    }

    @Override
    public RoleResourceRelPO get(RoleResourceRelPO roleResourceRelPO) {
        return roleResourceRelMapper.selectOne(roleResourceRelPO);
    }

    @Override
    public int update(RoleResourceRelPO roleResourceRelPO) {
        roleResourceRelPO.setUpdateTime(new Date());
        return roleResourceRelMapper.updateByPrimaryKeySelective(roleResourceRelPO);
    }

    @Override
    public int delete(RoleResourceRelPO roleResourceRelPO) {
        roleResourceRelPO.setIsDelete(UserConstant.IS_DELETE);
        roleResourceRelPO.setUpdateTime(new Date());
        return roleResourceRelMapper.updateByPrimaryKeySelective(roleResourceRelPO);
    }

    @Override
    public List<RoleResourceRelPO> queryList(RoleResourceRelPO roleResourceRelPO) {
        Example example = new Example(RoleResourceRelPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDelete",UserConstant.IS_NOTE_DELETE);
        if(StringUtils.isNotEmpty(roleResourceRelPO.getResourceId())){
            criteria.andEqualTo("resourceId",roleResourceRelPO.getResourceId());
        }
        if(StringUtils.isNotEmpty(roleResourceRelPO.getRoleId())){
            criteria.andEqualTo("roleId",roleResourceRelPO.getRoleId());
        }
        example.setOrderByClause("create_time desc");
        return roleResourceRelMapper.selectByExample(example);
    }

    @Override
    public int batchDelete(List<String> ids, String userId, String userName) {
        return 0;
    }
}
