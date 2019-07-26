package com.hhq.codegeneration.service.impl;
import com.hhq.codegeneration.mapper.DataBaseConfigMapper;
import com.hhq.codegeneration.po.DataBaseConfigPO;
import com.hhq.codegeneration.service.DataBaseConfigService;
import com.hhq.common.base.AbstractService;
import com.hhq.common.constant.BaseConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class DataBaseConfigServiceImpl extends AbstractService<DataBaseConfigPO> implements DataBaseConfigService {

    @Autowired
    private DataBaseConfigMapper dataBaseConfigMapper;

    @Override
    public int save(DataBaseConfigPO dataBaseConfigPO) {
        return dataBaseConfigMapper.insertSelective(dataBaseConfigPO);
    }

    @Override
    public DataBaseConfigPO get(DataBaseConfigPO dataBaseConfigPO) {
        return dataBaseConfigMapper.selectOne(dataBaseConfigPO);
    }

    @Override
    public int update(DataBaseConfigPO dataBaseConfigPO) {
        return dataBaseConfigMapper.updateByPrimaryKeySelective(dataBaseConfigPO);
    }

    @Override
    public int delete(DataBaseConfigPO dataBaseConfigPO) {
        return dataBaseConfigMapper.deleteByPrimaryKey(dataBaseConfigPO);
    }

    @Override
    public List<DataBaseConfigPO> queryList(DataBaseConfigPO dataBaseConfigPO) {
        Example example = new Example(DataBaseConfigPO.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(dataBaseConfigPO.getConfigName())){
            criteria.andLike("configName",dataBaseConfigPO.getConfigName());
        }
        example.setOrderByClause("create_time desc");
        return dataBaseConfigMapper.selectByExample(example);
    }

    @Override
    public int batchDelete(List<String> ids,String userId,String userName) {
        Example example = new Example(DataBaseConfigPO.class);
        Example.Criteria criteria = example.createCriteria();
        DataBaseConfigPO dataBaseConfigPO = new DataBaseConfigPO();
        dataBaseConfigPO.setIsDelete(BaseConstant.IS_DELETE);
        dataBaseConfigPO.setUpdateTime(new Date());
        dataBaseConfigPO.setUpdateUserId(userId);
        dataBaseConfigPO.setUpdater(userName);
        criteria.andIn("configId",ids);
        return dataBaseConfigMapper.updateByExampleSelective(dataBaseConfigPO,example);
    }
}


