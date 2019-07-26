package com.hhq.codegeneration.service;

import com.hhq.codegeneration.po.DataBaseColumnPO;
import com.hhq.codegeneration.po.DataBaseConfigPO;

import java.util.List;

/**
 * 数据库service
 */
public interface DataBaseService {


    /**
     * 根据数据库名查询表列表
     * @param tableSchema 数据库名
     * @param dataBaseConfigPO 数据库连接信息
     * @return
     */
    public List<String> queryTableListBySchema(String tableSchema, DataBaseConfigPO dataBaseConfigPO);


    /**
     * 根据表名查询列列表
     * @param tableName 表名
     * @param dataBaseConfigPO 数据库连接信息
     * @return
     */
    public List<DataBaseColumnPO> queryColumnListByTableName(String tableName,DataBaseConfigPO dataBaseConfigPO);

}
