package com.hhq.gateway.code.controller;

import com.hhq.codegeneration.po.TemplatePO;
import com.hhq.codegeneration.service.TemplateService;
import com.hhq.common.constant.BaseConstant;
import com.hhq.common.util.IdWorkerUtil;
import com.hhq.common.util.JsonUtils;
import com.hhq.common.util.ModelMapperUtil;
import com.hhq.gateway.code.vo.TemplateVO;
import com.hhq.gateway.constant.CommonConstants;
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
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /**
     * 查询列表
     * @param vo
     * @return
     */
    @RequestMapping("/queryList")
    public String queryList(@RequestBody @Validated TemplateVO vo){
        TemplatePO templatePO = ModelMapperUtil.strictMap(vo, TemplatePO.class);
        List<TemplatePO> dataList = templateService.queryList(templatePO);
        return JsonUtils.getSucc(dataList);
    }

    /**
     * 新增
     * @param vo
     * @return
     */
        @RequestMapping("/add")
    public String add(@RequestBody @Validated(value = {Insert.class}) TemplateVO vo){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        TemplatePO templatePO = ModelMapperUtil.strictMap(vo, TemplatePO.class);
        TemplatePO query = new TemplatePO();
        query.setTemplateName(vo.getTemplateName());
        query.setTemplateGroup(vo.getTemplateGroup());
        TemplatePO queryData = templateService.get(query);
        if(null!=queryData){
            return    JsonUtils.getFail(CommonConstants.NAME_EXIST,CommonConstants.NAME_EXIST_MSG);
        }

        templatePO.setTemplateId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());
        templatePO.setIsDelete(BaseConstant.IS_NOTE_DELETE);
        templatePO.setCreateTime(new Date());
        templatePO.setCreateUserId(user.getCreateUserId());
        templatePO.setCreator(user.getUserName());
        templateService.save(templatePO);
        return JsonUtils.getSucc();
    }
    /**
     * 更新
     * @param vo
     * @return
     */
    @RequestMapping("/update")
    public String update(@RequestBody @Validated(value = {Insert.class}) TemplateVO vo){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        TemplatePO templatePO = ModelMapperUtil.strictMap(vo, TemplatePO.class);
        templatePO.setUpdateTime(new Date());
        templatePO.setUpdateUserId(user.getUserId());
        templatePO.setUpdater(user.getUserName());
        templateService.update(templatePO);

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
        templateService.batchDelete(ids,user.getUserId(),user.getUserName());
        return JsonUtils.getSucc();
    }
}
