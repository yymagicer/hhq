package com.hhq.customer.constant;

/**
 * 客户常量
 */
public class CustomerConstant {

    /**
     * 手机号已注册
     */
    public static final  Integer MOBILE_HAS_REGIST = 2001;
    /**
     * 手机号已注册描述
     */
    public static final String MOBILE_HAS_REGIST_MSG = "mobile has regist";

    /**
     * 用户名已注册
     */
    public static final  Integer USER_NAME_HAS_REGIST = 2002;
    /**
     * 用户名已注册描述
     */
    public static final String USER_NAME_HAS_REGIST_MSG = "user name has regist";

    /**
     * 邮箱已注册
     */
    public static final  Integer EMAIL_HAS_REGIST = 2003;
    /**
     * 邮箱已注册描述
     */
    public static final String EMAIL_HAS_REGIST_MSG = "email has regist";
    /**
     * 注册类型：1-手机号
     */
    public static final String REGIST_TYPE_MOBILE = "1";
    /**
     * 注册类型：2-用户名；
     */
    public static final String REGIST_TYPE_USER_NAME = "2";
    /**
     * 注册类型：3-电子邮箱
     */
    public static final String REGIST_TYPE_EMAIL = "3";
    /**
     * 登陆类型：1-密码登陆
     */
    public static final String LOGIN_TYPE_PASS_WORD = "1";

    /**
     * 登陆类型：2-手机验证码登陆
     */
    public static final String LOGIN_TYPE_MOBILE_CODE = "2";

    /**
     * 登陆类型：3-邮箱登陆
     */
    public static final String LOGIN_TYPE_EMAIL_CODE = "2";

    /**
     * 用户名为空
     */
    public static final  Integer USER_NAME_IS_EMPTY= 2004;

    /**
     * 用户名为空描述
     */
    public static final String USER_NAME_IS_EMPTY_MSG = "user name is empty";

    /**
     * 密码为空
     */
    public static final  Integer PASSWORD_IS_EMPTY= 2005;

    /**
     * 密码为空描述
     */
    public static final String PASSWORD_IS_EMPTY_MSG = "password is empty";
}
