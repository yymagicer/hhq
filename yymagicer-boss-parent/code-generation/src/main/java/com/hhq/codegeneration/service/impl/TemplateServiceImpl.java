package com.hhq.codegeneration.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhq.codegeneration.mapper.TemplateMapper;
import com.hhq.codegeneration.po.TemplatePO;
import com.hhq.codegeneration.service.TemplateService;
import com.hhq.common.base.AbstractService;
import com.hhq.common.base.PageQueryParam;
import com.hhq.common.base.PageResult;
import com.hhq.common.util.PageUtils;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl extends AbstractService<TemplatePO> implements TemplateService {
    @Autowired
    private TemplateMapper templateMapper;
    @Override
    public int save(TemplatePO templatePO) {
        return templateMapper.insertSelective(templatePO);
    }

    @Override
    public TemplatePO get(TemplatePO templatePO) {
        return templateMapper.selectOne(templatePO);
    }

    @Override
    public int update(TemplatePO templatePO) {
        return templateMapper.updateByPrimaryKeySelective(templatePO);
    }

    @Override
    public int delete(TemplatePO templatePO) {
        return templateMapper.delete(templatePO);
    }

    @Override
    public List<TemplatePO> queryList(TemplatePO templatePO) {
        return templateMapper.select(templatePO);
    }

    @Override
    public Template getTemplate(String name) throws Exception{
        Template template = null;
        TemplatePO templatePO =  new TemplatePO();
        templatePO.setTemplateName(name);
        TemplatePO temp = templateMapper.selectOne(templatePO);
        if(null!=temp){
            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate(temp.getTemplateName(),temp.getTemplateContent());
            Configuration cfg = new Configuration();
            cfg.setTemplateLoader(stringLoader);
             template = cfg.getTemplate(temp.getTemplateName());
        }
        return template;
    }

    @Override
    public int batchDelete(List<String> ids, String userId, String userName) {
        return 0;
    }
}
