package com.hhq.user.po;

import com.hhq.common.base.BasePO;
import lombok.Data;

import javax.persistence.Table;

/**
 * 资源po
 */
@Data
@Table(name="t_resource")
public class ResourcePO extends BasePO {
    /**
     * 资源id
     */
    private String resourceId;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源类型1：菜单 2：按钮
     */
    private String resourceType;
    /**
     * 资源链接地址
     */
    private String resourceUrl;
    /**
     * 资源排序
     */
    private Integer resourceOrder;
    /**
     * 资源图标
     */
    private String icon;
    /**
     * 资源描述
     */
    private String resourceDesc;
    /**
     * 系统id
     */
    private String sysId;
}
