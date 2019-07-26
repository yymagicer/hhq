package com.hhq.gateway.code.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class GenerateCodeVO implements Serializable {

    /**
     * 对象的名称
     */
    @NotEmpty(message = "对象的名称不能为空")
    private String beanName;
    /**
     *表名
     */
    @NotEmpty(message = "表名不能为空")
    private String tableName;
    /**
     * 生成代码字段类配置id列表
     */
    @NotNull(message = "生成代码字段类配置id列表不能为空")
    private List<String> columns;

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
    @NotEmpty(message = "模板分组不能为空")
    private String templateGroup;
    /**
     * 基本路径
     */
    @NotEmpty(message = "基本路径不能为空")
    private String basePackage;
}
