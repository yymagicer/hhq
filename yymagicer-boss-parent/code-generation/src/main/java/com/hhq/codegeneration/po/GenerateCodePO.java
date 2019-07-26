package com.hhq.codegeneration.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GenerateCodePO implements Serializable {

        /**
         * java对象的名称
         */
        private String beanName;
        /**
         * 表名
         */
        private String tableName;
        /**
         * 生成代码字段类配置列表
         */
        private List<ColumnPO> columnList;

        /**
         * 列id集合
         */
        private List<String> columnIds;
        /**
         * 生成文件路径
         */
        private String path;
        /**
         * xml文件路径
         */
        private String xmlPath;
        /**
         * 模板分组
         */
        private String templateGroup;

        /**
         * 基本路径
         */
        private String basePackage;
}
