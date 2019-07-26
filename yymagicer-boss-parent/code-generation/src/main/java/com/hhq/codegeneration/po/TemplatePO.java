package com.hhq.codegeneration.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.Table;

@Table(name="t_template")
@Data
public class TemplatePO extends BasePO {
    /**
     *
     */
    private Integer id;
    /**
     * 主键
     */
    private String templateId;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板内容
     */
    private String templateContent;
    /**
     * 分组
     */
    private String templateGroup;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 生成文件名包名
     */
    private String generateFilePackage;
}
