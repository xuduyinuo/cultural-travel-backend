package com.xudu.culturaltravelbackend.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: GetRequestUtil
 * @description: TODO
 * @author: xudu
 * @create: 2024-11-07
 */
public class GetRequestUtil {

    public static HttpServletRequest getRequest() {

        // 获取请求
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        return request;
    }
}

