package com.hhq.user.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class WechatResponse extends BaseResponse {

    /**
     * 用户唯一标识
     */
    @JSONField(name = "openid")
    private String openId;

    /**
     * 会话密钥
     */
    @JSONField(name = "session_key")
    private String sessionKey;

    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     */
    @JSONField(name = "unionid")
    private String unionId;
}
