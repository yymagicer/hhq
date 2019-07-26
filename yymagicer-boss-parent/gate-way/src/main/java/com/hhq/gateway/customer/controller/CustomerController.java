package com.hhq.gateway.customer.controller;

import com.hhq.common.base.BaseController;
import com.hhq.common.base.BaseReturnResult;
import com.hhq.common.util.JsonUtils;
import com.hhq.common.util.ModelMapperUtil;
import com.hhq.customer.constant.CustomerConstant;
import com.hhq.customer.factory.RegistCustomerFactory;
import com.hhq.customer.po.CustomerPO;
import com.hhq.customer.service.impl.EmailRegistCustomerServiceImpl;
import com.hhq.customer.service.impl.UserNameRegistCustomerServiceImpl;
import com.hhq.gateway.constant.CommonConstants;
import com.hhq.gateway.customer.vo.CustomerVO;
import com.hhq.gateway.customer.vo.LoginVO;
import com.hhq.gateway.shiro.CustomUsernamePasswordToken;
import com.hhq.gateway.user.vo.LoginRespVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController {
    /**
     * 注册用户
     * @param vo
     * @return
     */
    @RequestMapping("/registCustomer")
    public String registCustomer(@Validated @RequestBody CustomerVO vo){
        CustomerPO customerPO = ModelMapperUtil.strictMap(vo, CustomerPO.class);
        BaseReturnResult<CustomerPO> result = null;
        if(CustomerConstant.REGIST_TYPE_EMAIL.equals(vo.getRegistType())){
            result = RegistCustomerFactory.getBean(EmailRegistCustomerServiceImpl.class).processRegist(customerPO);
        }else if(CustomerConstant.REGIST_TYPE_USER_NAME.equals(vo.getRegistType())){
            result = RegistCustomerFactory.getBean(UserNameRegistCustomerServiceImpl.class).processRegist(customerPO);
        }
        else{
            return JsonUtils.getFail("not support regist type");
        }
        if(!result.getSuccess()){
            return JsonUtils.getFail(result.getCode(),result.getMsg());
        }
        return JsonUtils.getSucc(result.getData().getUserId());
    }

    /**
     * 登陆
     * @param vo
     * @return
     */
    @RequestMapping("/login")
    public String login(@Validated @RequestBody LoginVO vo){
        Subject subject = SecurityUtils.getSubject();
        CustomUsernamePasswordToken  customUsernamePasswordToken = null;
        if(CustomerConstant.LOGIN_TYPE_PASS_WORD.equals(vo.getLoginType())){
            customUsernamePasswordToken = new CustomUsernamePasswordToken(vo.getLoginName(),vo.getPassword(),CommonConstants.USER_TYPE_C,vo);
        }else if(CustomerConstant.LOGIN_TYPE_MOBILE_CODE.equals(vo.getLoginType())||CustomerConstant.LOGIN_TYPE_EMAIL_CODE.equals(vo.getLoginType())){
            customUsernamePasswordToken = new CustomUsernamePasswordToken(vo.getLoginName(),null,CommonConstants.USER_TYPE_C,vo);
        }else{
            return JsonUtils.getFail(CommonConstants.NOT_SUPPORT_LOGIN_TYPE,CommonConstants.NOT_SUPPORT_LOGIN_TYPE_MSG);
        }
        subject.login(customUsernamePasswordToken);
        CustomerPO customer = (CustomerPO)subject.getPrincipal();
        LoginRespVO resp = new LoginRespVO();
        resp.setUserId(customer.getUserId());
        resp.setUserName(customer.getUserName());
        resp.setHtk(subject.getSession().getId().toString());
        return JsonUtils.getSucc(resp);
    }
}
