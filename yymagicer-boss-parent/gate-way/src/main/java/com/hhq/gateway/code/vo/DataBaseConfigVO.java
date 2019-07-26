package com.hhq.gateway.code.vo;

import com.hhq.common.base.BaseVO;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 *
 */
@Data
public class DataBaseConfigVO extends BaseVO implements Serializable {

    /**
     * 配置名称
     */
    @NotEmpty(groups ={Insert.class},message = "配置名称不能为空")
    private String configName;
    /**
     * 驱动名称
     */
    @NotEmpty(groups ={Insert.class},message = "驱动名称不能为空")
    private String driverName;
    /**
     * 数据库连接地址
     */
    @NotEmpty(groups ={Insert.class},message = "数据库连接地址不能为空")
    private String url;
    /**
     * 用户名
     */
    @NotEmpty(groups ={Insert.class},message = "用户名不能为空")
    private String userName;
    /**
     * 密码
     */
    @NotEmpty(groups ={Insert.class},message = "密码不能为空")
    private String password;
    /**
     *数据库名称
     */
    @NotEmpty(groups ={Insert.class},message = "数据库名称不能为空")
    private String tableSchema;
}
