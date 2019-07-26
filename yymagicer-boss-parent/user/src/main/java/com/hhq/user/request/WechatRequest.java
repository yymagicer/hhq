package com.hhq.user.request;

import lombok.Builder;
import lombok.Data;

/**
 * 微信请求
 */
@Data
@Builder
public class WechatRequest implements BaseRequest{

    /**
     * 小程序 appId
     */
    private String appId;

    /**
     * 小程序 appSecret
     */
    private String secret;

    /**
     * 登录时获取的 code
     */
    private String jsCode;

    /**
     * 授权类型，此处只需填写 authorization_code
     *
     */
    private String grantType;
}
