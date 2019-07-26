package com.hhq.gateway.code.vo;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ColumnVO implements Serializable {
    /**
     * 表名
     */
    @NotEmpty(groups = {Insert.class},message = "表名不为空")
    private String tableName;
    /**
     *表的字段名
     */
    @NotEmpty(groups = {Insert.class},message = "表的字段名不为空")
    private String columnName;

    /**
     * 是否可以为空
     */
    @NotEmpty(groups = {Insert.class},message = "nullable不为空")
    private Boolean nullable;

    /**
     * 字段数据类型
     */
    @NotEmpty(groups = {Insert.class},message = "字段数据类型不为空")
    private String dataType;
    /**
     * 最大长度
     */
    @NotEmpty(groups = {Insert.class},message = "最大长度不为空")
    private Long maxLength;
    /**
     * 是否为主键
     */
    @NotEmpty(groups = {Insert.class},message = "是否为主键不为空")
    private Boolean isKey;
    /**
     * 是否自增长
     */
    @NotEmpty(groups = {Insert.class},message = "是否自增长不为空")
    private Boolean isAutoIncrement;

    /**
     * 字段描述
     */
    @NotEmpty(groups = {Insert.class},message = "字段描述不为空")
    private String columnComment;
    /**
     * 字段对应的属性名称
     */
    @NotEmpty(groups = {Insert.class},message = "字段对应的属性名称不为空")
    private String propertyName;

    /**
     *  字段对应的属性对应的数据类型
     */
    @NotEmpty(groups = {Insert.class},message = "字段对应的属性对应的数据类型不为空")
    private String propertyType;
    /**
     * 展示类型：1-文本（text）；2-密码框（password）；3-单选框（radio）；4-复选框（checkbox）；5-按钮（button）；6-图片（image）；7-隐藏（hidden）
     */
    @NotEmpty(groups = {Insert.class},message = "展示类型不为空")
    private String showType;
    /**
     * 是否是查询条件
     */
    @NotEmpty(groups = {Insert.class},message = "查询条件不为空")
    private Boolean isQuery;
}
