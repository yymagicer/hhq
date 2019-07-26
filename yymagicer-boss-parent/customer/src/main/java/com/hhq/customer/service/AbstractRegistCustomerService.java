package com.hhq.customer.service;

import com.hhq.common.base.BaseReturnResult;
import com.hhq.common.util.JsonUtils;
import com.hhq.common.util.PageUtils;
import com.hhq.customer.po.CustomerPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AbstractRegistCustomerService implements  RegistCustomerService{

    @Autowired
    private CustomerService customerService;
    /**
     * 客户信息
     */
    private CustomerPO customerPO;

    @Override
    public BaseReturnResult<CustomerPO> beforeRegist(CustomerPO customerPO) {
        return new BaseReturnResult<CustomerPO>(true);
    }

    @Override
    public BaseReturnResult<CustomerPO> afterRegist(CustomerPO customerPO) {
        return new BaseReturnResult<CustomerPO>(true);
    }
    @Override
    @Transactional
    public BaseReturnResult<CustomerPO> processRegist(CustomerPO customerPO) {
        BaseReturnResult<CustomerPO> returnResult = beforeRegist(customerPO);
        if(!returnResult.getSuccess()){
            return returnResult;
        }
        customerService.save(customerPO);
        returnResult = afterRegist(customerPO);
        returnResult.setData(customerPO);
        return returnResult;
    }
}
