package com.hhq.gateway.code.vo;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class TableVO implements Serializable {
    /**
     * 表名
     */
    @NotEmpty(groups = {Insert.class},message = "表名不能为空")
    private String tableName;

    /**
     * 表描述
     */
    @NotEmpty(groups = {Insert.class},message = "表描述不能为空")
    private String tableContent;
    /**
     * 类名
     */
    @NotEmpty(groups = {Insert.class},message = "类名不能为空")
    private String className;
}
