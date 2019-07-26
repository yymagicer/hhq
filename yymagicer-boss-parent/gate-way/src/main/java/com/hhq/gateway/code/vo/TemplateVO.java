package com.hhq.gateway.code.vo;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class TemplateVO implements Serializable {

    /**
     * 模板名称
     */
    @NotEmpty(groups = {Insert.class},message = "模板名称不为空")
    private String templateName;
    /**
     * 模板内容
     */
    @NotEmpty(groups = {Insert.class},message = "模板内容不为空")
    private String templateContent;
    /**
     * 分组
     */
    @NotEmpty(groups = {Insert.class},message = "分组不为空")
    private String templateGroup;

    /**
     * 文件类型
     */
    @NotEmpty(groups = {Insert.class},message = "文件类型不为空")
    private String fileType;
    /**
     * 生成文件名包名
     */
    @NotEmpty(groups = {Insert.class},message = "生成文件名包名不为空")
    private String generateFilePackage;
}
