package com.hhq.customer.service;

import com.hhq.common.base.BaseService;
import com.hhq.customer.po.CustomerPO;

/**
 * 客户service
 */
public interface CustomerService extends BaseService<CustomerPO> {

    /**
     * 根据手机号查询
     * @param mobile
     * @return
     */
    CustomerPO queryByMobile(String mobile);

    /**
     * 根据用户名称查询
     * @param userName
     * @return
     */
    CustomerPO queryByUserName(String userName);

    /**
     * 根据电子邮箱查询
     * @param email
     * @return
     */
    CustomerPO queryByEmail(String email);
}
