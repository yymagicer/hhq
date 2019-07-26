package com.hhq.codegeneration.po;

import lombok.Data;

@Data
public class QueryDataBaseColumnParamPO {
    /**
     * 数据库名
     */
    private String tableSchema;

    /**
     * 表名
     */
    private String tableName;
    /**
     * 列名
     */
    private String columnName;
}
