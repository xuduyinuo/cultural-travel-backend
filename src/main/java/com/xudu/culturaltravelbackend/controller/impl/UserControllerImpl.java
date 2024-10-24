package com.xudu.culturaltravelbackend.controller.impl;

import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.controller.UserController;
import com.xudu.culturaltravelbackend.model.dto.userdto.LoginRequest;
import com.xudu.culturaltravelbackend.model.dto.userdto.RegisterRequest;
import com.xudu.culturaltravelbackend.service.UserService;
import com.xudu.culturaltravelbackend.utils.RedisUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @className: UserControllerImpl
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-10
 */
@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;

    @Override
    public Result hello() {
        return Result.success(10);
    }

    @Override
    public Result registerUser(@RequestBody RegisterRequest registerRequest) {
        String userAccount = registerRequest.getUserAccount();
        String userPassword = registerRequest.getUserPassword();
        String checkPassword = registerRequest.getCheckPassword();

        Long userId = userService.RegisterUser(userAccount, userPassword, checkPassword);

        return Result.success(userId);
    }

    @Override
    public Result loginUser(@RequestBody LoginRequest loginRequest) {
        return null;
    }
}
