package com.hhq.codegeneration.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.Table;

/**
 *
 */
@Data
@Table(name="t_table_column")
public class ColumnPO extends BasePO {
	private Integer id;
	/**
	 * 主键
	 */
	private String  columnId;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 *表的字段名
	 */
	private String columnName;

	/**
	 * 是否可以为空
	 */
	private String nullable;

	/**
	 * 字段数据类型
	 */
	private String dataType;
	/**
	 * 最大长度
	 */
	private Long maxLength;
	/**
	 * 是否为主键
	 */
	private String isKey;

	/**
	 * 是否唯一
	 */
	private String isUnique;
	/**
	 * 是否自增长
	 */
	private String isAutoIncrement;

	/**
	 * 字段描述
	 */
	private String columnComment;
	/**
	 * 字段对应的属性名称
	 */
	private String propertyName;

	/**
	 *  字段对应的属性对应的数据类型
	 */
	private String propertyType;
	/**
	 * 展示类型：1-文本（text）；2-密码框（password）；3-单选框（radio）；4-复选框（checkbox）；5-按钮（button）；6-图片（image）；7-隐藏（hidden）
	 */
	private String showType;
	/**
	 * 是否是查询条件
	 */
	private String isQuery;
	/**
	 * 查询类型：0-等于查询；1-like查询；2-or查询；3-in查询；4-between查询；5-小于查询；6-小于等于查询；7-大于查询；8-大于等于查询；9-不等于查询
	 */
	private String queryType;
}
