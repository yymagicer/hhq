package com.hhq.user.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 基类响应
 */
@Data
public class BaseResponse {

    /**
     * 错误码
     */
    @JSONField(name = "errcode")
    private String errCode;

    /**
     * 错误信息
     */
    @JSONField(name = "errmsg")
    private String errMsg;

}
