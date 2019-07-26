package com.hhq.gateway.user.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class SetUserRoleVO implements Serializable {

    @NotEmpty(message = "userId不能为空")
    private String userId;
    @NotNull(message = "角色id不能为null")
    private List<String> roleIds;
}
