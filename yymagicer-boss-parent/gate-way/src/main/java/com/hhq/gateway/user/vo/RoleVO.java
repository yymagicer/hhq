package com.hhq.gateway.user.vo;

import com.hhq.common.base.PageQueryParam;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
public class RoleVO extends PageQueryParam implements Serializable {

    /**
     * 角色id
     */
    @NotEmpty(message = "角色id不能为空",groups ={ Update.class, Delete.class})
    private String roleId;
    /**
     * 角色编号
     */
    private String roleCode;
    /**
     * 角色名称
     */
    @NotEmpty(message = "角色名称不能为空")
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;

    private String key;


    private String value;

    private List<Route> routes;
}
