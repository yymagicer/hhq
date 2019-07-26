package com.hhq.codegeneration.po;

import lombok.Data;

@Data
public class DataBaseColumnPO {

    /**
     *
     */
    private String tableCatalog;

    /**
     * 表所属的库
     */
    private String tableSchema;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;
    /**
     * 字段标识
     *
     * 从1开始往后排
     */
    private Integer  ordinalPosition;

    /**
     * 字段默认值
     */
    private String columnDefault;

    /**
     * 字段是否可以是NULL
     *
     * 该列记录的值是YES或者NO
     */
    private String isNullable;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 字段的最大字符数。
     *
     * 假如字段设置为varchar(50)，那么这一列记录的值就是50
     *
     * 该列只适用于二进制数据，字符，文本，图像数据。其他类型数据比如int，float，datetime等，在该列显示为NULL
     */
    private Integer characterMaximumLength;

    /**
     * 字段的最大字节数。
     *
     * 和最大字符数一样，只适用于二进制数据，字符，文本，图像数据，其他类型显示为NULL。
     *
     * 和最大字符数的数值有比例关系，和字符集有关。比如UTF8类型的表，最大字节数就是最大字符数的3倍
     */
    private Integer characterOctetLength;

    /**
     * 数字精度。
     *
     * 适用于各种数字类型比如int，float之类的。
     *
     * 如果字段设置为int(10)，那么在该列保存的数值是9，少一位，还没有研究原因。
     *
     * 如果字段设置为float(10,3)，那么在该列的数值是10。
     *
     * 非数字类型显示为在该列NULL。
     */
    private Integer numericPrecision;

    /**
     * 小数位数。
     *
     * 和数字精度一样，适用于各种数字类型比如int，float之类。
     *
     * 如果字段设置为int(10)，那么在该列保存的数值是0，代表没有小数。
     *
     * 如果字段设置为float(10,3)，那么在该列的数值是3。
     *
     * 非数字类型显示为在该列NULL
     */
    private Integer numericScale;

    /**
     * datetime类型和SQL-92interval类型数据库的子类型代码。
     *
     * 我本地datetime类型的字段在该列显示为0。
     *
     * 其他类型显示为NULL。
     */
    private Integer datetimePrecision;
    /**
     * 字段字符集名称。比如utf8。
     */
    private String characterSetName;

    /**
     * 字符集排序规则。
     *
     * 比如utf8_general_ci，是不区分大小写一种排序规则。utf8_general_cs，是区分大小写的排序规则。
     */
    private String collationName;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 索引类型。
     *
     * 可包含的值有PRI，代表主键，UNI，代表唯一键，MUL，可重复。
     */
    private String columnKey;

    /**
     * 其他信息。
     *
     * 比如主键的auto_increment
     */
    private String extra;

    /**
     * 权限
     *
     * 多个权限用逗号隔开，比如 select,insert,update,references
     */
    private String privileges;

    /**
     * 字段注释
     */
    private String columnComment;
}
