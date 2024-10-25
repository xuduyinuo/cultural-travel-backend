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
    NOT_LOGIN_ERROR(403, "未登录"),
    NO_AUTH_ERROR(404, "无权限"),
    NOT_FOUND_ERROR(405, "请求数据不存在"),
    FORBIDDEN_ERROR(406, "禁止访问"),
    OPERATION_ERROR(407, "操作失败"),
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
