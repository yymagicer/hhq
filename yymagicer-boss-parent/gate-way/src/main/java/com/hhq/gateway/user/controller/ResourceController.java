package com.hhq.gateway.user.controller;

import com.github.pagehelper.PageInfo;
import com.hhq.common.base.BaseController;
import com.hhq.common.base.PageResult;
import com.hhq.common.constant.UserConstant;
import com.hhq.common.util.JsonUtils;
import com.hhq.common.util.ModelMapperUtil;
import com.hhq.common.util.PageUtils;
import com.hhq.gateway.user.vo.ResourceVO;
import com.hhq.gateway.user.vo.RoleVO;
import com.hhq.user.po.ResourcePO;
import com.hhq.user.po.RolePO;
import com.hhq.user.po.UserPO;
import com.hhq.user.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/resource")
@Slf4j
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 分页查询
     * @param vo
     * @return
     */
    @RequestMapping("/queryListByPage")
    public String queryListByPage(@RequestBody ResourceVO vo){
        ResourcePO resourcePO = ModelMapperUtil.strictMap(vo, ResourcePO.class);
        vo.setT(resourcePO);
        PageInfo<ResourcePO> resourcePOPageInfo = resourceService.queryListByPage(vo);
        PageResult pageResult = PageUtils.parseBasePageResult(resourcePOPageInfo);
        log.info(JsonUtils.getSucc(pageResult));
        return JsonUtils.getSucc(pageResult);
    }

    /**
     * 新增
     * @param vo
     * @return
     */
    @RequestMapping("/addResource")
    public String addResource(@RequestBody ResourceVO vo){
        Subject subject = SecurityUtils.getSubject();
        UserPO user = (UserPO) subject.getPrincipal();

        ResourcePO resourcePO = resourceService.queryByName(vo.getResourceName());
        if(null!=resourcePO){
            return JsonUtils.getFail(UserConstant.RESOURCE_NAME_EXIST,UserConstant.RESOURCE_NAME_EXIST_MSG);
        }
        resourcePO = ModelMapperUtil.strictMap(vo,ResourcePO.class);
        resourcePO.setCreateUserId(user.getUserId());
        resourcePO.setCreator(user.getUserName());
        resourceService.save(resourcePO);
        return JsonUtils.getSucc();
    }

    /**
     * 修改
     * @param vo
     * @return
     */
    @RequestMapping("/updateResource")
    public String updateResource(@Validated(Update.class) @RequestBody ResourceVO vo){
        Subject subject = SecurityUtils.getSubject();
        UserPO user = (UserPO) subject.getPrincipal();
        ResourcePO resourcePO = resourceService.queryByResourceId(vo.getResourceId());
        if(null!=resourcePO){
            if(!resourcePO.getResourceName().equals(vo.getResourceName())){
                ResourcePO temp = resourceService.queryByName(vo.getResourceName());
                if(null==temp){
                    return JsonUtils.getFail(UserConstant.RESOURCE_NAME_EXIST,UserConstant.RESOURCE_NAME_EXIST_MSG);
                }
            }
            resourcePO.setResourceName(vo.getResourceName());
            resourcePO.setResourceType(vo.getResourceType());
            resourcePO.setResourceUrl(vo.getResourceUrl());
            resourcePO.setResourceOrder(vo.getResourceOrder());
            resourcePO.setIcon(vo.getIcon());
            resourcePO.setResourceDesc(vo.getResourceDesc());
            resourcePO.setSysId(vo.getSysId());
            resourcePO.setUpdateUserId(user.getUserId());
            resourcePO.setUpdater(user.getUserName());
            resourcePO.setUpdateTime(new Date());
            resourceService.update(resourcePO);
        }
        return JsonUtils.getSucc();
    }

    /**
     * 删除角色
     * @param resourceId
     * @return
     */
    @RequestMapping("/deleteResource")
    public String deleteResource(@RequestParam("resourceId") String resourceId){
        Subject subject = SecurityUtils.getSubject();
        UserPO user = (UserPO) subject.getPrincipal();
        ResourcePO resourcePO = resourceService.queryByResourceId(resourceId);
        if(null!=resourcePO){
            resourceService.delete(resourcePO);
        }
        return JsonUtils.getSucc();
    }
}
