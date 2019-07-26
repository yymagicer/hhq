package com.hhq.codegeneration.service.impl;

import com.hhq.codegeneration.po.DataBaseColumnPO;
import com.hhq.codegeneration.po.DataBaseConfigPO;
import com.hhq.codegeneration.service.DataBaseService;
import com.hhq.codegeneration.util.DBUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据库service实现
 */
@Service
public class DataBaseServiceImpl implements DataBaseService {

    @Override
    public List<String> queryTableListBySchema(String tableSchema, DataBaseConfigPO dataBaseConfigPO) {
        List<String> dataList = new ArrayList<>();
        String sql = new StringBuilder( DBUtil.SQL).append(" where TABLE_SCHEMA = '").append(tableSchema).append("'").toString();
        try{
            List<DataBaseColumnPO> list =  (List<DataBaseColumnPO>)DBUtil.build(dataBaseConfigPO).getSqlData(sql, DataBaseColumnPO.class, true);
            if(null!=list){
                dataList =   list.stream().map(DataBaseColumnPO::getTableName).distinct().collect(Collectors.toList());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataList;
    }
    @Override
    public List<DataBaseColumnPO> queryColumnListByTableName(String tableName, DataBaseConfigPO dataBaseConfigPO) {
        String sql = new StringBuilder( DBUtil.SQL).append(" where TABLE_NAME = '").append(tableName).append("'").toString();
        List<DataBaseColumnPO> list = new ArrayList<>();
        try{
            list =  (List<DataBaseColumnPO>)DBUtil.build(dataBaseConfigPO).getSqlData(sql, DataBaseColumnPO.class, true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
