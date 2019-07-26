package com.hhq.customer.service;

import com.hhq.common.base.BaseReturnResult;
import com.hhq.customer.po.CustomerPO;
import com.hhq.customer.po.extend.LoginPO;
import org.springframework.stereotype.Service;

@Service
public class AbstractCustomerLoginService implements CustomerLoginService {

    @Override
    public BaseReturnResult<CustomerPO> beforeLogin(LoginPO loginPO) {
        return new BaseReturnResult<CustomerPO>(true);
    }

    @Override
    public BaseReturnResult<CustomerPO> afterLogin(LoginPO loginPO) {
        return new BaseReturnResult<CustomerPO>(true);
    }

    @Override
    public BaseReturnResult<CustomerPO> processLogin(LoginPO loginPO) {
        BaseReturnResult<CustomerPO> baseReturnResult = beforeLogin(loginPO);
        if(!baseReturnResult.getSuccess()){
            return baseReturnResult;
        }


        return null;
    }
}
