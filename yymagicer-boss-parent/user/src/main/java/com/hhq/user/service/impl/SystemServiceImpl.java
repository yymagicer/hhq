package com.hhq.user.service.impl;

import com.hhq.common.base.AbstractService;
import com.hhq.common.constant.UserConstant;
import com.hhq.common.util.IdWorkerUtil;
import com.hhq.user.mapper.SystemMapper;
import com.hhq.user.po.SystemPO;
import com.hhq.user.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class SystemServiceImpl extends AbstractService<SystemPO> implements SystemService {

    @Autowired
    private SystemMapper systemMapper;

    @Override
    public int save(SystemPO systemPO) {
        systemPO.setSysId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
        systemPO.setIsDelete(UserConstant.IS_NOTE_DELETE);
        systemPO.setCreateTime(new Date());
        return systemMapper.insertSelective(systemPO);
    }

    @Override
    public SystemPO get(SystemPO systemPO) {
        return systemMapper.selectOne(systemPO);
    }

    @Override
    public int update(SystemPO systemPO) {
        systemPO.setUpdateTime(new Date());
        return systemMapper.updateByPrimaryKeySelective(systemPO);
    }

    @Override
    public int delete(SystemPO systemPO) {
        systemPO.setIsDelete(UserConstant.IS_DELETE);
        systemPO.setUpdateTime(new Date());
        return systemMapper.updateByPrimaryKeySelective(systemPO);
    }

    @Override
    public List<SystemPO> queryList(SystemPO systemPO) {
        Example example = new Example(SystemPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDelete",UserConstant.IS_NOTE_DELETE);
        if(StringUtils.isNotEmpty(systemPO.getSysName())){
            criteria.andLike("sysName",systemPO.getSysName());
        }
        example.setOrderByClause("create_time desc");
        return systemMapper.selectByExample(example);
    }

    @Override
    public int batchDelete(List<String> ids, String userId, String userName) {
        return 0;
    }
}
