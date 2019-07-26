package com.hhq.gateway.constant;


import com.hhq.common.constant.BaseConstant;
import com.hhq.gateway.util.YmlConfigUtil;

import java.util.ResourceBundle;

public class CommonConstants extends BaseConstant {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    // 转换坐标所需要的配置信息
    public final static String testValue = resourceBundle.getString("testValue");

    // 转换坐标所需要的配置信息
    public final static String testValue2 = YmlConfigUtil.getConfigByKey("testValue");

    public static final Integer FAIL = -1;
    /**
     * 用户未登陆
     */
    public static final Integer USER_NOT_LOGIN = 1001;

    public static final String USER_NOT_LOGIN_MSG = "用户未登陆";

    /**
     * 名称已存在
     */
    public static final Integer NAME_EXIST = 1002;

    public static final String NAME_EXIST_MSG = "名称已存在";

    /**
     * 文件不存在
     */
    public static final Integer FILE_EMPTY = 1003;

    public static final String FILE_EMPTY_MSG = "名称已存在";
    /**
     * 客户端用户
     */
    public static final String USER_TYPE_C = "1";
    /**
     * 后台用户
     */
    public static final String USER_TYPE_B = "2";
    /**
     * redis用户验证码前缀
     */
    public static final String HHQ_LOGIN_REDIS_PREFIX = "hhq:login:vcode:";
    /**
     * hhq密码
     */
    public static final String HHQ_PASSWORD = "hhq";
    /**
     * 不支持的登陆方式
     */
    public static final Integer NOT_SUPPORT_LOGIN_TYPE = 1004;
    /**
     * 不支持的登陆方式描述
     */
    public static final String NOT_SUPPORT_LOGIN_TYPE_MSG = "not support login type";
}
