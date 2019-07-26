package com.hhq.gateway.user.controller;

import com.hhq.common.util.JsonUtils;
import com.hhq.gateway.constant.CommonConstants;
import com.hhq.gateway.shiro.CustomUsernamePasswordToken;
import com.hhq.gateway.user.vo.LoginRespVO;
import com.hhq.gateway.user.vo.UserLoginVO;
import com.hhq.user.po.UserPO;
import com.hhq.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;
    /**
     * 登陆
     *
     * @param request
     * @param vo
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, @Validated @RequestBody UserLoginVO vo) {
        CustomUsernamePasswordToken  customUsernamePasswordToken = new CustomUsernamePasswordToken(vo.getUsername(),vo.getPassword(),CommonConstants.USER_TYPE_B,null);
        Subject subject = SecurityUtils.getSubject();
        subject.login(customUsernamePasswordToken);
        UserPO user = (UserPO) subject.getPrincipal();
        LoginRespVO resp = new LoginRespVO();
        resp.setUserId(user.getId()+"");
        resp.setUserName(user.getUserName());
        resp.setUserRealName(user.getUserRealName());
        resp.setPassword(user.getPassword());
        resp.setHtk(subject.getSession().getId().toString());
        resp.setToken(subject.getSession().getId().toString());
        log.info(JsonUtils.getSucc(resp));
        return JsonUtils.getSucc(resp);
    }

    /**
     * 注销
     *
     * @return
     */
    @RequestMapping("/loginOut")
    public String loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return JsonUtils.getSucc();
    }
}
