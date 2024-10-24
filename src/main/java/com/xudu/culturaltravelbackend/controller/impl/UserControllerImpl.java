package com.xudu.culturaltravelbackend.controller.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xudu.culturaltravelbackend.common.DeleteRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.controller.UserController;
import com.xudu.culturaltravelbackend.model.dto.userdto.LoginRequest;
import com.xudu.culturaltravelbackend.model.dto.userdto.RegisterRequest;
import com.xudu.culturaltravelbackend.model.dto.userdto.SearchUserRequest;
import com.xudu.culturaltravelbackend.model.dto.userdto.UpdateUserRequest;
import com.xudu.culturaltravelbackend.model.vo.UserVO;
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
    public Result registerUser(RegisterRequest registerRequest) throws Exception {
        String userAccount = registerRequest.getUserAccount();
        String userPassword = registerRequest.getUserPassword();
        String checkPassword = registerRequest.getCheckPassword();

        Long userId = userService.RegisterUser(userAccount, userPassword, checkPassword);

        return Result.success(userId);
    }

    @Override
    public Result loginUser(LoginRequest loginRequest) throws Exception {

        String userAccount = loginRequest.getUserAccount();
        String userPassword = loginRequest.getUserPassword();

        UserVO userVO = userService.LoginUser(userAccount, userPassword);
        return Result.success(userVO);
    }

    @Override
    public Result logoutUser() {
        return null;
    }

    @Override
    public Result searchUserList(SearchUserRequest searchUserRequest, HttpServletRequest request) {
        Page<UserVO> userVOPage = userService.getUserListByPage(searchUserRequest, request);
        return Result.success(userVOPage);
    }

    @Override
    public Result updateUser(UpdateUserRequest updateUserRequest, HttpServletRequest request) {
        return null;
    }

    @Override
    public Result deleteUser(DeleteRequest deleteRequest, HttpServletRequest request) {
        return null;
    }
}
