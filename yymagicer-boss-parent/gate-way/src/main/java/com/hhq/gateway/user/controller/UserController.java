package com.hhq.gateway.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hhq.common.base.PageQueryParam;
import com.hhq.common.base.PageResult;
import com.hhq.common.constant.UserConstant;
import com.hhq.common.util.*;
import com.hhq.gateway.constant.CommonConstants;
import com.hhq.gateway.user.vo.*;
import com.hhq.gateway.vo.PageQueryParamVO;
import com.hhq.user.po.UserPO;
import com.hhq.user.service.UserRoleRelService;
import com.hhq.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Update;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleRelService userRoleRelService;


    @GetMapping("/getUserInfo")
    private String getUserInfo(HttpServletRequest request){
        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
        UserInfoRespVO userInfo = ModelMapperUtil.strictMap(user, UserInfoRespVO.class);
        RoleVO role = new RoleVO();
        Route route = new Route();
        route.setName("Dashboard");
        route.setPath("dashboard");
        JSONObject meta = new JSONObject();
        meta.put("title","dashboard");
        meta.put("icon","dashboard");
        route.setMeta(meta);
        role.setKey("admin");
        role.setValue("admin");
        List<Route> routes = Arrays.asList(route);
        role.setRoutes(routes);
        userInfo.setRoles(Arrays.asList(role));
        return JsonUtils.getSucc(userInfo);
    }
    @RequestMapping("/queryListByPage")
    public String queryListByPage(@RequestBody QueryUserByPageVO vo){
        UserPO userPO = ModelMapperUtil.strictMap(vo, UserPO.class);
        vo.setT(userPO);
        PageInfo<UserPO> userPOPageInfo = userService.queryListByPage(vo);
        PageResult pageResult = PageUtils.parseBasePageResult(userPOPageInfo);
        LOGGER.info(JsonUtils.getSucc(pageResult));
        return JsonUtils.getSucc(pageResult);
    }

    @RequestMapping("/addUser")
    public String addUser(@Valid @RequestBody UserVO userVO){
        UserPO userPO = ModelMapperUtil.strictMap(userVO, UserPO.class);
        List<UserPO> userList = userService.queryList(userPO);
        if(CollectionUtils.isNotEmpty(userList)){
            return JsonUtils.getFail(UserConstant.USER_NAME_EXIST,UserConstant.USER_NAME_EXIST_MSG);
        }
        //处理用户密码
        String salt = SecretUtils.getSalt();
        String userId = IdWorkerUtil.getFlowIdWorkerInstance().nextId();
        userPO.setSalt(salt);
        userPO.setPassword(SecretUtils.generateStoredPwd(salt,userPO.getPassword()));
        userPO.setUserId(userId);
        userPO.setIsDelete(CommonConstants.IS_NOTE_DELETE);
        userPO.setCreateTime(new Date());
        userService.save(userPO);
        return JsonUtils.getSucc(userId);
    }
    @RequestMapping("/updateUser")
    public String updateUser(@Validated({Update.class}) @RequestBody UserVO userVO){
        UserPO user = userService.queryByUserId(userVO.getUserId());
        if(null==user){
            return JsonUtils.getFail(UserConstant.USER_NOT_EXIST,UserConstant.USER_NOT_EXIST_MSG);
        }
        if(!user.getUserName().equals(userVO.getUserName())){
            UserPO tempUser = userService.getByName(userVO.getUserName());
            if(null!=tempUser){
                return JsonUtils.getFail(UserConstant.USER_NAME_EXIST,UserConstant.USER_NAME_EXIST_MSG);
            }
            user.setUserName(userVO.getUserName());
        }
        user.setPassword(SecretUtils.generateStoredPwd(user.getSalt(),userVO.getPassword()));
        user.setSex(userVO.getSex());
        user.setMobile(userVO.getMobile());
        user.setEmail(userVO.getEmail());
        userService.update(user);
        return JsonUtils.getSucc();
    }
    /**
     * 用户注册
     * @param vo
     * @return
     */
    @RequestMapping("/regist")
    public String regist(@RequestBody UserVO vo){
        UserPO userPO = ModelMapperUtil.strictMap(vo, UserPO.class);
        userPO= userService.registUser(userPO);
        return JsonUtils.getSucc(userPO);
    }
    @RequestMapping("/setUserRole")
    public String setUserRole(@RequestBody SetUserRoleVO vo){
        UserPO user = userService.queryByUserId(vo.getUserId());
        if(null==user){
            return JsonUtils.getFail(UserConstant.USER_NOT_EXIST,UserConstant.USER_NOT_EXIST_MSG);
        }
        userRoleRelService.setUserRoles(vo.getUserId(),vo.getRoleIds());
        return JsonUtils.getSucc();
    }
}
