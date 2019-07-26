package com.hhq.gateway.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.hhq.user.po.UserPO;
import com.hhq.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserList")
    public String getUserList(){
        List<UserPO> userPOList = userService.queryList(new UserPO());
        return JSONObject.toJSONString(userPOList);
    }
    @RequestMapping("/getUserList2")
    public String getUserList2(@RequestBody JSONObject object){
        List<UserPO> userPOList = userService.queryList(new UserPO());
        return JSONObject.toJSONString(userPOList);
    }

    @RequestMapping("/getUserList3")
    public String getUserList3(){
        List<UserPO> userPOList = userService.getList();
        return JSONObject.toJSONString(userPOList);
    }
}
