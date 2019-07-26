package com.hhq.customer.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 *客户po
 */
@Table(name = "t_customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPO extends BasePO {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 盐
     */
    private String salt;
    /**
     * 设备类型：1-小程序；2-安卓；3-ios；4-pc
     */
    private String deviceType;
}
