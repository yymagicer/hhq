package com.hhq.customer.service;

import com.hhq.common.base.BaseReturnResult;
import com.hhq.customer.po.CustomerPO;
import com.hhq.customer.po.extend.LoginPO;

/**
 * 客户登陆service
 */
public interface CustomerLoginService {

    /**
     * 登陆前方法
     * @param loginPO
     * @return
     */
    BaseReturnResult<CustomerPO> beforeLogin(LoginPO loginPO);

    /**
     * 登陆后方法
     * @param loginPO
     * @return
     */
    BaseReturnResult<CustomerPO> afterLogin(LoginPO loginPO);

    /**
     * 登陆注册
     * @param loginPO
     * @return
     */
    BaseReturnResult<CustomerPO> processLogin(LoginPO loginPO);
}
