package com.xudu.culturaltravelbackend.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Result {

    private int code;

    private String message;

    private Object data;




    //成功返回信息
    public static Result success(Object data){
        return Result.builder()
                .code(200)
                .message("ok")
                .data(data)
                .build();
    }

    //返回成功不反回数据，返回自定义成功信息
    public static Result success(String message){
        return Result.builder()
                .code(200)
                .message(message)
                .data(null)
                .build();
    }

    //错误返回信息
    public static Result error(ErrorCode errorCode){
        return Result.builder()
                .code(errorCode.getCode())
                .data(null)
                .message(errorCode.getMessage())
                .build();

    }

    public static Result error(ErrorCode errorCode, String message){
        return Result.builder()
                .code(errorCode.getCode())
                .message(message)
                .build();
    }


    public static Result error(int code, String message) {
        return Result.builder()
                .code(code)
                .message(message)
                .data(null)
                .build();
    }


}