package com.hhq.gateway.user.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.hhq.common.base.BaseController;
import com.hhq.common.base.PageResult;
import com.hhq.common.constant.UserConstant;
import com.hhq.common.util.JsonUtils;
import com.hhq.common.util.ModelMapperUtil;
import com.hhq.common.util.PageUtils;
import com.hhq.gateway.user.vo.RoleVO;
import com.hhq.user.po.RolePO;
import com.hhq.user.po.TreeDataPO;
import com.hhq.user.po.UserPO;
import com.hhq.user.service.RoleService;
import com.hhq.user.service.UserRoleRelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleRelService userRoleRelService;

    /**
     * 分页查询
     * @param vo
     * @return
     */
    @RequestMapping("/queryListByPage")
    public String queryListByPage(@RequestBody RoleVO vo){
        RolePO rolePO = ModelMapperUtil.strictMap(vo, RolePO.class);
        vo.setT(rolePO);
        PageInfo<RolePO> rolePOPageInfo = roleService.queryListByPage(vo);
        PageResult pageResult = PageUtils.parseBasePageResult(rolePOPageInfo);
        log.info(JsonUtils.getSucc(pageResult));
        return JsonUtils.getSucc(pageResult);
    }

    /**
     * 新增
     * @param vo
     * @return
     */
    @RequestMapping("/addRole")
    public String addRole(@RequestBody RoleVO vo){
        Subject subject = SecurityUtils.getSubject();
        UserPO user = (UserPO) subject.getPrincipal();
        RolePO rolePO = roleService.queryByName(vo.getRoleName());
        if(null!=rolePO){
            return JsonUtils.getFail(UserConstant.ROLE_NAME_EXIST,UserConstant.ROLE_NAME_EXIST_MSG);
        }
        rolePO = ModelMapperUtil.strictMap(vo,RolePO.class);
        rolePO.setCreateUserId(user.getUserId());
        rolePO.setCreator(user.getUserName());
        roleService.save(rolePO);
        return JsonUtils.getSucc();
    }

    /**
     * 修改
     * @param vo
     * @return
     */
    @RequestMapping("/updateRole")
    public String updateRole(@Validated(Update.class) @RequestBody RoleVO vo){
        Subject subject = SecurityUtils.getSubject();
        UserPO user = (UserPO) subject.getPrincipal();
        RolePO rolePO = roleService.queryByRoleId(vo.getRoleId());
        if(null!=rolePO){
            if(!rolePO.getRoleName().equals(vo.getRoleName())){
                RolePO temp = roleService.queryByName(vo.getRoleName());
                if(null==temp){
                    return JsonUtils.getFail(UserConstant.ROLE_NAME_EXIST,UserConstant.ROLE_NAME_EXIST_MSG);
                }
            }
            rolePO.setRoleName(vo.getRoleName());
            rolePO.setRoleDesc(vo.getRoleDesc());
            rolePO.setUpdateUserId(user.getUserId());
            rolePO.setUpdater(user.getUserName());
            rolePO.setUpdateTime(new Date());
            roleService.update(rolePO);
        }
        return JsonUtils.getSucc();
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @RequestMapping("/deleteRole")
    public String deleteRole(@RequestParam("roleId") String roleId){
        Subject subject = SecurityUtils.getSubject();
        UserPO user = (UserPO) subject.getPrincipal();
        RolePO rolePO = roleService.queryByRoleId(roleId);
        if(null!=rolePO){
            roleService.delete(rolePO);
        }
        return JsonUtils.getSucc();
    }

    /**
     * 查询
     * @return
     */
    @RequestMapping("/getRoleList")
    public String getRoleList(){
        RolePO rolePO =  new RolePO();
        rolePO.setParentRoleId("-1");
        List<RolePO> roles = roleService.queryList(rolePO);
        JSONArray treeArray = getTreeArray(roles);
        return JsonUtils.getSucc(treeArray);
    }

    @RequestMapping("/getRoleListByParentId")
    public String  getRoleListByParentId(@RequestParam("parentId") String parentId){
        RolePO rolePO =  new RolePO();
        rolePO.setParentRoleId(parentId);
        List<RolePO> roles = roleService.queryList(rolePO);
        JSONArray treeArray = getTreeArray(roles);
        return JsonUtils.getSucc(treeArray);
    }
    private JSONArray getTreeArray( List<RolePO> roles){
        JSONArray array = new JSONArray();
        roles.forEach(item->{
            TreeDataPO data = new TreeDataPO();
            data.setId(item.getRoleId());
            data.setName(item.getRoleName());
            array.add(data);
        });
        return array;
    }
}
