package com.hhq.user.utils;

import com.hhq.user.constant.WeChatConstant;

import java.text.MessageFormat;

/**
 * url工具类
 */
public class UrlBuilderUtil {

    /**
     * 获取微信小程序登录凭证校验url
     * @param appId 小程序 appId
     * @param secret 小程序 appSecret
     * @param jsCode 登录时获取的 code
     * @return
     */
    public static String getAuthCode2SessionUrl(String appId,String secret,String jsCode){
        return MessageFormat.format(WeChatConstant.AUTH_CODE2SESSION_URL,appId,secret,jsCode);
    }
}
