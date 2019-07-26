package com.hhq.customer.service;

import com.hhq.common.base.BaseReturnResult;
import com.hhq.customer.po.CustomerPO;

/**
 * 注册用户接口
 */
public interface RegistCustomerService {

    /**
     * 注册前方法
     * @param customerPO
     * @return
     */
    BaseReturnResult<CustomerPO> beforeRegist(CustomerPO customerPO);

    /**
     * 注册后方法
     * @param customerPO
     * @return
     */
    BaseReturnResult<CustomerPO> afterRegist(CustomerPO customerPO);

    /**
     * 处理注册
     * @param customerPO
     * @return
     */
    BaseReturnResult<CustomerPO> processRegist(CustomerPO customerPO);
}
