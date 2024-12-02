package com.xudu.culturaltravelbackend.constant;

/**
 * 用户常量
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "user_login";

    //  region 权限

    /**
     * 普通用户
     */
    int DEFAULT_ROLE = 0;

    /**
     * 管理员
     */
    int ADMIN_ROLE = 1;

    /**
     * ban
     */
    int BAN_ROLE = 2;

}