package com.xudu.culturaltravelbackend.common;

/**
 * @className: ErrorCode
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-10
 */
public enum ErrorCode {

    SUCCESS(200, "OK"),
    PARAMS_ERROR(401,"请求参数错误"),
    NULL_ERROR(402,"请求数据为空"),
    NOT_LOGIN(403, "未登录"),
    NO_AUTH(404, "无权限"),
    SYSTEM_ERROR(500, "系统错误"),
    CUSTOM_ERROR(1000, "");

    private int code;

    private String message;


    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
