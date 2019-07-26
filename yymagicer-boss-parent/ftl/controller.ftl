package ${basePackage}.controller;

import com.github.pagehelper.PageInfo;
import ${basePackage}.po.${beanName?cap_first}PO;
import ${basePackage}.service.${beanName?cap_first}Service;
import com.hhq.common.base.PageQueryParam;
import com.hhq.common.constant.BaseConstant;
import com.hhq.common.util.IdWorkerUtil;
import com.hhq.common.util.JsonUtils;
import com.hhq.common.util.ModelMapperUtil;
import ${basePackage}.gateway.${beanName?lower_case}.vo.${beanName?cap_first}VO;
import ${basePackage}.gateway.constant.CommonConstants;
import com.hhq.user.po.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/${beanName?uncap_first}")
public class ${beanName?cap_first}Controller {

    @Autowired
    private ${beanName?cap_first}Service ${beanName?uncap_first}Service;

    /**
     * 分页查询
     * @param vo
     * @return
     */
    @RequestMapping("/queryListByPage")
    public String queryListByPage(@RequestBody @Validated ${beanName?cap_first}VO vo){
        ${beanName?cap_first}PO ${beanName?uncap_first}PO = ModelMapperUtil.strictMap(vo, ${beanName?cap_first}PO.class);
        PageQueryParam<${beanName?cap_first}PO> param  =new PageQueryParam<>();
        param.setPageSize(vo.getPageSize());
        param.setPageNum(vo.getPageNum());
        param.setT(${beanName?uncap_first}PO);
        PageInfo<${beanName?cap_first}PO> pageInfo = ${beanName?uncap_first}Service.queryListByPage(param);
        return JsonUtils.getSucc(pageInfo);
    }

    /**
     * 查询列表
     * @param vo
     * @return
     */
    @RequestMapping("/queryList")
    public String queryList(@RequestBody @Validated ${beanName?cap_first}VO vo){
        ${beanName?cap_first}PO ${beanName?uncap_first}PO = ModelMapperUtil.strictMap(vo, ${beanName?cap_first}PO.class);
        List<${beanName?cap_first}PO> dataList = ${beanName?uncap_first}Service.queryList(${beanName?uncap_first}PO);
        return JsonUtils.getSucc(dataList);
    }

    /**
     * 新增
     * @param vo
     * @return
     */
    @RequestMapping("/add")
    public String add(@RequestBody @Validated(value = {Insert.class}) ${beanName?cap_first}VO vo){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        ${beanName?cap_first}PO ${beanName?uncap_first}PO = ModelMapperUtil.strictMap(vo, ${beanName?cap_first}PO.class);
		<#list columnList as column>
			<#if column.isUnique ?? && column.isUnique=='0'>
        ${beanName?cap_first}PO query = new ${beanName?cap_first}PO();
		query.set${column.propertyName?cap_first}(vo.get{column.propertyName?cap_first});
        ${beanName?cap_first}PO queryByUnqiue = ${beanName?uncap_first}Service.get(query);
        if(null!=queryByUnqiue){
         return    JsonUtils.getFail(CommonConstants.NAME_EXIST,CommonConstants.NAME_EXIST_MSG);
        }
			<#break> 
           </#if>
		</#list>
        
        ${beanName?uncap_first}PO.setConfigId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
        ${beanName?uncap_first}PO.setIsDelete(BaseConstant.IS_NOTE_DELETE);
        ${beanName?uncap_first}PO.setCreateTime(new Date());
        ${beanName?uncap_first}PO.setCreateUserId(user.getCreateUserId());
        ${beanName?uncap_first}PO.setCreator(user.getUserName());
        ${beanName?uncap_first}Service.save(${beanName?uncap_first}PO);
        return JsonUtils.getSucc();
    }
    /**
     * 更新
     * @param vo
     * @return
     */
    @RequestMapping("/update")
    public String update(@RequestBody @Validated(value = {Insert.class}) ${beanName?cap_first}VO vo){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        ${beanName?cap_first}PO ${beanName?uncap_first}PO = ModelMapperUtil.strictMap(vo, ${beanName?cap_first}PO.class);
        ${beanName?uncap_first}PO.setUpdateTime(new Date());
        ${beanName?uncap_first}PO.setUpdateUserId(user.getUserId());
        ${beanName?uncap_first}PO.setUpdater(user.getUserName());
        ${beanName?uncap_first}Service.update(${beanName?uncap_first}PO);
        return JsonUtils.getSucc();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("ids") List<String> ids){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        ${beanName?uncap_first}Service.batchDelete(ids,user.getUserId(),user.getUserName());
        return JsonUtils.getSucc();
    }
}
