package com.xudu.culturaltravelbackend.config;

import com.xudu.culturaltravelbackend.interceptor.AdminInterceptor;
import com.xudu.culturaltravelbackend.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //管理员拦截器配置
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns()//拦截所有请求
                .excludePathPatterns("/**");


        //登陆拦截器配置
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns()//拦截所有请求
                .excludePathPatterns("/**");
    }

    // @Bean
    // WebMvcConfigurer webMvcConfigurer(){
    //     return new WebMvcConfigurer(){
    //         @Override
    //         public void addInterceptors(InterceptorRegistry registry) {
    //             registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**");
    //         }
    //     };
    // }

}