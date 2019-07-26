package com.hhq.codegeneration.service.impl;

import com.hhq.codegeneration.mapper.TableMapper;
import com.hhq.codegeneration.po.DataBaseConfigPO;
import com.hhq.codegeneration.po.TablePO;
import com.hhq.codegeneration.service.TableService;
import com.hhq.common.base.AbstractService;
import com.hhq.common.constant.BaseConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * table service实现
 */
@Service
public class TableServiceImpl extends AbstractService<TablePO> implements TableService {
    @Autowired
    private TableMapper tableMapper;
    @Override
    public int save(TablePO tablePO) {
        return tableMapper.insertSelective(tablePO);
    }

    @Override
    public TablePO get(TablePO tablePO) {
        return tableMapper.selectOne(tablePO);
    }

    @Override
    public int update(TablePO tablePO) {
        return tableMapper.updateByPrimaryKeySelective(tablePO);
    }

    @Override
    public int delete(TablePO tablePO) {
        return tableMapper.deleteByPrimaryKey(tablePO);
    }

    @Override
    public List<TablePO> queryList(TablePO tablePO) {
        Example example = new Example(TablePO.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(tablePO.getTableName())){
            criteria.andLike("table_name",tablePO.getTableName());
        }
        example.setOrderByClause("create_time desc");
        return tableMapper.selectByExample(example);
    }

    @Override
    public int batchDelete(List<String> ids, String userId, String userName) {
        Example example = new Example(DataBaseConfigPO.class);
        Example.Criteria criteria = example.createCriteria();
        TablePO tablePO = new TablePO();
        tablePO.setIsDelete(BaseConstant.IS_DELETE);
        tablePO.setUpdateTime(new Date());
        tablePO.setUpdateUserId(userId);
        tablePO.setUpdater(userName);
        criteria.andIn("tableId",ids);
        return tableMapper.updateByExampleSelective(tablePO,example);
    }
}
