package com.hhq.gateway.user.vo;

import com.hhq.common.base.PageQueryParam;
import com.hhq.gateway.vo.PageQueryParamVO;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 用户VO
 */
@Data
public class UserVO extends PageQueryParam implements Serializable {
    private Integer id;
    /**
     * 用户id
     */
    @NotEmpty(message = "用户id不能为空",groups = Update.class)
    private String userId;
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    private String userRealName;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "性别不能为空")
    private String sex;
    @NotEmpty(message = "手机号不能为空")
    private String mobile;
    private String email;
    private String headImage;
    private String salt;
    /**
     *用户角色
     */
    private List<String> userRoles;
}
