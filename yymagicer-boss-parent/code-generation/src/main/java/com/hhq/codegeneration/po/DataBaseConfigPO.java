package com.hhq.codegeneration.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="t_database_config")
@Data
public class DataBaseConfigPO extends BasePO {
    @Id
    @GeneratedValue(generator="JDBC")
    private Integer id ;
    /**
     * 主键
     */
    private String configId;
    /**
     * 配置名称
     */
    private String configName;
    /**
     * 驱动名称
     */
    private String driverName;
    /**
     * 数据库连接地址
     */
    private String url;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    /**
     *数据库名称
     */
    private String tableSchema;
}
