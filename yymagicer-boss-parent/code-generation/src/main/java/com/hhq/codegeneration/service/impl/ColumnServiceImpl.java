package com.hhq.codegeneration.service.impl;

import com.hhq.codegeneration.mapper.ColumnMapper;
import com.hhq.codegeneration.po.ColumnPO;
import com.hhq.codegeneration.service.ColumnService;
import com.hhq.common.base.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ColumnServiceImpl extends AbstractService<ColumnPO> implements ColumnService {

    @Autowired
    private ColumnMapper columnMapper;
    @Override
    public int save(ColumnPO columnPO) {
        return columnMapper.insertSelective(columnPO);
    }
    @Override
    public ColumnPO get(ColumnPO columnPO) {
        return columnMapper.selectByPrimaryKey(columnPO);
    }

    @Override
    public int update(ColumnPO columnPO) {
        return columnMapper.updateByPrimaryKeySelective(columnPO);
    }

    @Override
    public int delete(ColumnPO columnPO) {
        return columnMapper.deleteByPrimaryKey(columnPO);
    }

    @Override
    public List<ColumnPO> queryList(ColumnPO columnPO) {
        Example example = new Example(ColumnPO.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(columnPO.getColumnName())){
            criteria.andLike("columnName",columnPO.getColumnName());
        }
        example.setOrderByClause("create_time desc");
        return columnMapper.selectByExample(example);
    }

    @Override
    public int batchDelete(List<String> ids, String userId, String userName) {
        return 0;
    }
}
