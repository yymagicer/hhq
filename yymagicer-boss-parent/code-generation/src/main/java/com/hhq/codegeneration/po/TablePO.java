package com.hhq.codegeneration.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_database_table")
@Data
public class TablePO extends BasePO {

    @Id
    @GeneratedValue(generator="JDBC")
    private Integer id;
    /**
     * 主键
     */
    private String tableId;
    /**
     * 表名
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableContent;
    /**
     * 类名
     */
    private String className;
}
