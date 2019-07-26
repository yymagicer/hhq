package com.hhq.user.constant;

/**
 * 微信常量类
 */
public class WeChatConstant {

    /**
     * 小程序登录凭证校验url
     */
    public static final String AUTH_CODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";

    /**
     * 请求成功
     */
    public static final String SUCCESS= "0";

    /**
     * 系统异常
     */
    public static final String SYSTEM_ERROR = "-1";

    /**
     * code 无效
     */
    public static final String JS_CODE_INVALID = "40029";
    /**
     * 频率限制，每个用户每分钟100次
     */
    public static final String FREQUENCY_LIMIT = "45011";

}
