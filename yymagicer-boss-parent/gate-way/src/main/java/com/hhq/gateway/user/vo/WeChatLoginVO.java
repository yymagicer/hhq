package com.hhq.gateway.user.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class WeChatLoginVO {

    /**
     * 登录时获取的 code
     */
    @NotEmpty(message = "jsCode不能为空")
    private String jsCode;
}
