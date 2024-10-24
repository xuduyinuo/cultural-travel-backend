package com.xudu.culturaltravelbackend.config;

import com.xudu.culturaltravelbackend.interceptor.MyHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MySpringMVCConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor())
                .addPathPatterns("/**");//拦截所有请求
                //.excludePathPatterns("/","/login","/css/**","/js/**","/img/**");
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