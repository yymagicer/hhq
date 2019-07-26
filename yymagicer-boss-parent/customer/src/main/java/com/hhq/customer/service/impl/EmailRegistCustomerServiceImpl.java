package com.hhq.customer.service.impl;

import com.hhq.common.base.BaseReturnResult;
import com.hhq.customer.constant.CustomerConstant;
import com.hhq.customer.po.CustomerPO;
import com.hhq.customer.service.AbstractRegistCustomerService;
import com.hhq.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 邮箱注册
 */
@Service
public class EmailRegistCustomerServiceImpl extends AbstractRegistCustomerService {

    @Autowired
    private CustomerService customerService;

    @Override
    public BaseReturnResult<CustomerPO> beforeRegist(CustomerPO customerPO) {
        BaseReturnResult<CustomerPO> result = new BaseReturnResult<>();
        CustomerPO temp = customerService.queryByEmail(customerPO.getEmail());
        if(null!=temp){
            result.setSuccess(false);
            result.setCode(CustomerConstant.EMAIL_HAS_REGIST);
            result.setMsg(CustomerConstant.EMAIL_HAS_REGIST_MSG);
            return result;
        }
        result.setSuccess(true);
        return result;
    }
}
