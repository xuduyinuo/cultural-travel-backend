package com.xudu.culturaltravelbackend.interceptor;

import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.exception.ServiceException;
import com.xudu.culturaltravelbackend.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员拦截器
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {

    //拦截器配置——告诉拦截器主要拦截哪些请求
    @Resource
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean admin = userService.isAdmin();
        if (!admin) {
            throw new ServiceException(ErrorCode.NO_AUTH_ERROR);
        }
        return true;
    }

    //@Override
    //public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    //    System.out.println("postHandle");
    //}
    //
    //@Override
    //public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    //    System.out.println("afterCompletion");
    //}


}