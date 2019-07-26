package com.hhq.user.service.impl;

import com.hhq.common.base.AbstractService;
import com.hhq.common.constant.UserConstant;
import com.hhq.common.util.IdWorkerUtil;
import com.hhq.user.mapper.ResourceMapper;
import com.hhq.user.po.ResourcePO;
import com.hhq.user.po.RolePO;
import com.hhq.user.service.ResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl extends AbstractService<ResourcePO> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public ResourcePO queryByName(String resourceName) {
        ResourcePO resourcePO = new ResourcePO();
        resourcePO.setIsDelete(UserConstant.IS_NOTE_DELETE);
        resourcePO.setResourceName(resourceName);
        return resourceMapper.selectOne(resourcePO);
    }

    @Override
    public ResourcePO queryByResourceId(String resourceId) {
        ResourcePO resourcePO = new ResourcePO();
        resourcePO.setResourceId(resourceId);
        return resourceMapper.selectOne(resourcePO);
    }

    @Override
    public int save(ResourcePO resourcePO) {
        resourcePO.setResourceId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
        resourcePO.setIsDelete(UserConstant.IS_NOTE_DELETE);
        resourcePO.setCreateTime(new Date());
        return resourceMapper.insertSelective(resourcePO);
    }

    @Override
    public ResourcePO get(ResourcePO resourcePO) {
        return resourceMapper.selectOne(resourcePO);
    }

    @Override
    public int update(ResourcePO resourcePO) {
        resourcePO.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKeySelective(resourcePO);
    }

    @Override
    public int delete(ResourcePO resourcePO) {
        resourcePO.setIsDelete(UserConstant.IS_DELETE);
        resourcePO.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKeySelective(resourcePO);
    }

    @Override
    public List<ResourcePO> queryList(ResourcePO resourcePO) {
        Example example = new Example(ResourcePO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDelete",UserConstant.IS_NOTE_DELETE);
        if(StringUtils.isNotEmpty(resourcePO.getResourceName())){
            criteria.andLike("resourceName",resourcePO.getResourceName());
        }
        if(StringUtils.isNotEmpty(resourcePO.getSysId())){
            criteria.andEqualTo("sysId",resourcePO.getSysId());
        }
        example.setOrderByClause("create_time desc");
        return resourceMapper.selectByExample(example);
    }
    @Override
    public int batchDelete(List<String> ids, String userId, String userName) {
        return 0;
    }
}
