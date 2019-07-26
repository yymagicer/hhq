package com.hhq.codegeneration.service.impl;

import com.hhq.codegeneration.mapper.ColumnMapper;
import com.hhq.codegeneration.mapper.TemplateMapper;
import com.hhq.codegeneration.po.ColumnPO;
import com.hhq.codegeneration.po.GenerateCodePO;
import com.hhq.codegeneration.po.TemplatePO;
import com.hhq.codegeneration.service.GenerateService;
import com.hhq.codegeneration.util.StringUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenerateServiceImpl implements GenerateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateServiceImpl.class);


    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private ColumnMapper columnMapper;
    @Override
    public void generateCode(GenerateCodePO po) {

        Example example = new Example(ColumnPO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("columnId",po.getColumnIds());
        List<ColumnPO> columnPOS = columnMapper.selectByExample(example);
        po.setColumnList(columnPOS);
        String templateGroup = po.getTemplateGroup();
        //获取模板信息
        TemplatePO query = new TemplatePO();
        query.setTemplateGroup(templateGroup);
        List<TemplatePO> templateList = templateMapper.select(query);
        templateList.forEach(item->{
            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate(item.getTemplateName(),item.getTemplateContent());
            Configuration cfg = new Configuration();
            cfg.setTemplateLoader(stringLoader);
            try{
                Template template = cfg.getTemplate(item.getTemplateName());
                processTemplate(template,po,item);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    /**
     * 处理模板
     * @param template
     * @param generateCodePO
     * @param templatePO
     */
    public void processTemplate(Template template,GenerateCodePO generateCodePO,TemplatePO templatePO){
        String beanName = generateCodePO.getBeanName();
        String path = generateCodePO.getPath();
        String xmlPath = generateCodePO.getXmlPath();
        String dir = "";
        String fileName = "";
        if("xml".equalsIgnoreCase(templatePO.getFileType())){
            dir = new StringBuilder(xmlPath).append(File.separator).append("mapper").toString();
            fileName = new StringBuilder(dir).append(File.separator).append(StringUtil.firstCharacterToUpper(beanName)).append("Mapper.xml").toString();
        }else if("po".equalsIgnoreCase(templatePO.getFileType())){
            dir = new StringBuilder(path).append(File.separator).append(templatePO.getGenerateFilePackage()).toString();
            fileName = new StringBuilder(dir).append(File.separator).append(StringUtil.firstCharacterToUpper(beanName)).append("PO.java").toString();
        }else if("vo".equalsIgnoreCase(templatePO.getFileType())){
            dir = new StringBuilder(path).append(File.separator).append(templatePO.getGenerateFilePackage()).toString();
            fileName = new StringBuilder(dir).append(File.separator).append(StringUtil.firstCharacterToUpper(beanName)).append("VO.java").toString();
        } else if("dao".equalsIgnoreCase(templatePO.getFileType())){
            dir = new StringBuilder(path).append(File.separator).append(templatePO.getGenerateFilePackage()).toString();
            fileName = new StringBuilder(dir).append(File.separator).append(StringUtil.firstCharacterToUpper(beanName)).append("Mapper.java").toString();
        }else if("service".equalsIgnoreCase(templatePO.getFileType())){
            dir = new StringBuilder(path).append(File.separator).append(templatePO.getGenerateFilePackage()).toString();
            fileName = new StringBuilder(dir).append(File.separator).append(StringUtil.firstCharacterToUpper(beanName)).append("Service.java").toString();
        }else if("serviceImpl".equalsIgnoreCase(templatePO.getFileType())){
            dir = new StringBuilder(path).append(File.separator).append(templatePO.getGenerateFilePackage()).toString();
            fileName = new StringBuilder(dir).append(File.separator).append(StringUtil.firstCharacterToUpper(beanName)).append("ServiceImpl.java").toString();
        }else if("controller".equalsIgnoreCase(templatePO.getFileType())){
            dir = new StringBuilder(path).append(File.separator).append(templatePO.getGenerateFilePackage()).toString();
            fileName = new StringBuilder(dir).append(File.separator).append(StringUtil.firstCharacterToUpper(beanName)).append("Controller.java").toString();
        }
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("beanName",generateCodePO.getBeanName());
        dataMap.put("tableName",generateCodePO.getTableName());
        dataMap.put("basePackage",generateCodePO.getBasePackage());
        dataMap.put("columnList",generateCodePO.getColumnList());
        dataMap.put("path",generateCodePO.getPath());
        dataMap.put("xmlPath",generateCodePO.getXmlPath());
        dataMap.put("fileType",templatePO.getFileType());
        dataMap.put("generateFilePackage",templatePO.getGenerateFilePackage());
        try {
            FileUtils.forceMkdir(new File(dir));
            try (FileWriter fw = new FileWriter(fileName);){
                template.process(dataMap, fw);
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        LOGGER.info("generate file：{}",fileName);
    }
}
