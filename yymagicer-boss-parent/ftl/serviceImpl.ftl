package ${basePackage}.service.impl;
import ${basePackage}.mapper.${beanName?cap_first}Mapper;
import ${basePackage}.po.${beanName?cap_first}PO;
import ${basePackage}.service.${beanName?cap_first}Service;
import com.hhq.common.base.AbstractService;
import com.hhq.common.constant.BaseConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class ${beanName?cap_first}ServiceImpl extends AbstractService<${beanName?cap_first}PO> implements ${beanName?cap_first}Service {

    @Autowired
    private ${beanName?cap_first}Mapper ${beanName?uncap_first}Mapper;

    @Override
    public int save(${beanName?cap_first}PO ${beanName?uncap_first}PO) {
        return ${beanName?uncap_first}Mapper.insertSelective(${beanName?uncap_first}PO);
    }

    @Override
    public ${beanName?cap_first}PO get(${beanName?cap_first}PO ${beanName?uncap_first}PO) {
        return ${beanName?uncap_first}Mapper.selectOne(${beanName?uncap_first}PO);
    }

    @Override
    public int update(${beanName?cap_first}PO ${beanName?uncap_first}PO) {
        return ${beanName?uncap_first}Mapper.updateByPrimaryKeySelective(${beanName?uncap_first}PO);
    }

    @Override
    public int delete(${beanName?cap_first}PO ${beanName?uncap_first}PO) {
        return ${beanName?uncap_first}Mapper.deleteByPrimaryKey(${beanName?uncap_first}PO);
    }

    @Override
    public List<${beanName?cap_first}PO> queryList(${beanName?cap_first}PO ${beanName?uncap_first}PO) {
        Example example = new Example(${beanName?cap_first}PO.class);
        return ${beanName?uncap_first}Mapper.selectByExample(example);
    }

    @Override
    public int batchDelete(List<String> ids,String userId,String userName) {
        Example example = new Example(${beanName?cap_first}PO.class);
        Example.Criteria criteria = example.createCriteria();
        ${beanName?cap_first}PO ${beanName?uncap_first}PO = new ${beanName?cap_first}PO();
        ${beanName?uncap_first}PO.setIsDelete(BaseConstant.IS_DELETE);
        ${beanName?uncap_first}PO.setUpdateTime(new Date());
        ${beanName?uncap_first}PO.setUpdateUserId(userId);
        ${beanName?uncap_first}PO.setUpdater(userName);
		<#list columnList as column>
			<#if column.isUnique ?? && column.isUnique=='0'>
        criteria.andIn("${column.propertyName}",ids);
				<#break> 
           </#if>
		</#list>
        return ${beanName?uncap_first}Mapper.updateByExampleSelective(${beanName?uncap_first}PO,example);
    }
}


