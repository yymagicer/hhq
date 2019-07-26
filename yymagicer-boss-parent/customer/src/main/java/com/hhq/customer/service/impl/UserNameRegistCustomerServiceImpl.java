package com.hhq.customer.service.impl;

import com.hhq.common.base.BaseReturnResult;
import com.hhq.customer.constant.CustomerConstant;
import com.hhq.customer.po.CustomerPO;
import com.hhq.customer.service.AbstractRegistCustomerService;
import com.hhq.customer.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNameRegistCustomerServiceImpl extends AbstractRegistCustomerService {

    @Autowired
    private CustomerService customerService;
    @Override
    public BaseReturnResult<CustomerPO> beforeRegist(CustomerPO customerPO) {
        BaseReturnResult<CustomerPO> result = new BaseReturnResult<>();
        if(StringUtils.isEmpty(customerPO.getUserName())){
            result.setSuccess(false);
            result.setCode(CustomerConstant.USER_NAME_IS_EMPTY);
            result.setMsg(CustomerConstant.USER_NAME_IS_EMPTY_MSG);
            return result;
        }
        if(StringUtils.isEmpty(customerPO.getPassword())){
            result.setSuccess(false);
            result.setCode(CustomerConstant.PASSWORD_IS_EMPTY);
            result.setMsg(CustomerConstant.PASSWORD_IS_EMPTY_MSG);
            return result;
        }
        CustomerPO temp = customerService.queryByUserName(customerPO.getUserName());
        if(null!=temp){
            result.setSuccess(false);
            result.setCode(CustomerConstant.USER_NAME_HAS_REGIST);
            result.setMsg(CustomerConstant.USER_NAME_HAS_REGIST_MSG);
            return result;
        }
        result.setSuccess(true);
        return result;
    }
}
