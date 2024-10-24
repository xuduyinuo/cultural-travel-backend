package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.common.DeleteRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.model.dto.userdto.LoginRequest;
import com.xudu.culturaltravelbackend.model.dto.userdto.RegisterRequest;
import com.xudu.culturaltravelbackend.model.dto.userdto.SearchUserRequest;
import com.xudu.culturaltravelbackend.model.dto.userdto.UpdateUserRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: UserController
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-10
 */
public interface UserController {

    @GetMapping("/hello")
    Result hello();


    /**
     * 注册用户
     * @param registerRequest 注册请求
     * @return 注册成功用户id
     * @throws Exception 加解密抛出异常
     */
    @PostMapping("/register")
    Result registerUser(@RequestBody RegisterRequest registerRequest) throws Exception;

    /**
     * 登录用户
     * @param loginRequest 登录请求
     * @return 登录成功用户信息
     * @throws Exception 加解密抛出异常
     */
    @PostMapping("/login")
    Result loginUser(@RequestBody LoginRequest loginRequest) throws Exception;

    /**
     * 退出登陆
     * @return 退出成功或失败信息
     */
    @PostMapping("/logout")
    Result logoutUser();

    /**
     * 搜索用户分页列表
     * @param request 请求
     * @return 用户分页列表
     */
    @GetMapping("/search/list/page")
    Result searchUserList(SearchUserRequest searchUserRequest, HttpServletRequest request);

    /**
     * 更新用户信息
     */
    @PostMapping("/update")
    Result updateUser(@RequestBody UpdateUserRequest updateUserRequest, HttpServletRequest request);

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    Result deleteUser(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request);
}
