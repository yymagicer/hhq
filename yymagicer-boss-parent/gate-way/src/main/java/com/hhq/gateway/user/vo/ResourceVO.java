package com.hhq.gateway.user.vo;

import com.hhq.common.base.PageQueryParam;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ResourceVO extends PageQueryParam implements Serializable {
    /**
     * 资源id
     */
    @NotEmpty(message = "资源id不能为空",groups ={ Update.class, Delete.class})
    private String resourceId;
    /**
     * 资源名称
     */
    @NotEmpty(message = "资源名称不能为空")
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
    @NotEmpty(message = "系统id不能为空")
    private String sysId;
}
