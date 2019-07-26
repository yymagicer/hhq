package com.hhq.codegeneration.service;

import com.hhq.codegeneration.po.TemplatePO;
import com.hhq.common.base.BaseService;
import freemarker.template.Template;

public interface TemplateService extends BaseService<TemplatePO> {

    public Template getTemplate(String name) throws Exception;
}
