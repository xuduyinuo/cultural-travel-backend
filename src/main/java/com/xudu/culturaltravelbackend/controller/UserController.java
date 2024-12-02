package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.annotation.AuthCheck;
import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.DeleteRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.constant.UserConstant;
import com.xudu.culturaltravelbackend.model.dto.userdto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@Api(tags = "用户模块")
public interface UserController {

    @ApiOperation("testhello")
    @GetMapping("/hello")
    Result hello();


    /**
     * 注册用户
     * @param registerRequest 注册请求
     * @return 注册成功用户id
     * @throws Exception 加解密抛出异常
     */
    @ApiOperation("注册用户")
    @PostMapping("/register")
    Result registerUser(@RequestBody RegisterRequest registerRequest) throws Exception;

    /**
     * 登录用户
     * @param loginRequest 登录请求
     * @return 登录成功用户信息
     * @throws Exception 加解密抛出异常
     */
    @ApiOperation("登录用户")
    @PostMapping("/login")
    Result loginUser(@RequestBody LoginRequest loginRequest) throws Exception;

    /**
     * 退出登陆
     * @return 退出成功或失败信息
     */
    @ApiOperation("退出登陆")
    @PostMapping("/logout")
    Result logoutUser();

    /**
     * 搜索用户分页列表
     * @param request 请求
     * @return 用户分页列表
     */
    @ApiOperation("搜索用户分页列表")
    @GetMapping("/search/list/page")
    Result searchUserList(SearchUserRequest searchUserRequest, HttpServletRequest request);

    /**
     * 更新用户信息
     */
    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    Result updateUser(UpdateUserRequest updateUserRequest);

    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @PostMapping("/delete")
    Result deleteUser(@RequestBody DeleteBatchRequest deleteBatchRequest, HttpServletRequest request);

    @ApiOperation("封禁用户")
    @PostMapping("/update/ban")
    Result banUser(@RequestBody BanUserRequest banUserRequest);
}
