package com.xudu.culturaltravelbackend.controller.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xudu.culturaltravelbackend.annotation.AuthCheck;
import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.constant.UserConstant;
import com.xudu.culturaltravelbackend.controller.UserController;
import com.xudu.culturaltravelbackend.model.dto.userdto.*;
import com.xudu.culturaltravelbackend.model.entity.User;
import com.xudu.culturaltravelbackend.model.vo.UserVO;
import com.xudu.culturaltravelbackend.service.UserService;
import com.xudu.culturaltravelbackend.utils.GetRequestUtil;
import com.xudu.culturaltravelbackend.utils.RedisUtil;
import com.xudu.culturaltravelbackend.utils.TokenUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @className: UserControllerImpl
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-10
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;

    @Override
    public Result hello() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserAccount, "xudu");
        User user = userService.getOne(queryWrapper);
        String userTags = user.getUserTags();
        return Result.success(userTags);
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
        // String token = TokenUtil.getTokenFromCookie(GetRequestUtil.getRequest());
        String token = userService.getLoginUser().getToken();
        Boolean b = redisUtil.deleteRedisContent(token);
        if (!b){
            return Result.error(ErrorCode.SYSTEM_ERROR, "退出失败");
        }
        return Result.success("退出成功");
    }

    //@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @Override
    public Result searchUserList(SearchUserRequest searchUserRequest, HttpServletRequest request) {
        Page<UserVO> userVOPage = userService.getUserListByPage(searchUserRequest, request);
        //判断搜索结果
        if (userVOPage.getTotal() == 0){
            return Result.error(ErrorCode.PARAMS_ERROR, "无搜索结果");
        }
        return Result.success(userVOPage);
    }

    @Override
    public Result updateUser(UpdateUserRequest updateUserRequest) {
        Boolean b = userService.updateUser(updateUserRequest);
        if (!b){
            return Result.error(ErrorCode.SYSTEM_ERROR, "更新失败");
        }
        return Result.success("更新成功");
    }

    @Override
    public Result deleteUser(DeleteBatchRequest deleteBatchRequest, HttpServletRequest request) {
        List<Long> ids = deleteBatchRequest.getIds();
        Integer deleteCount = userService.deleteUser(ids);
        return Result.success(deleteCount);
    }

    @Override
    public Result banUser(BanUserRequest banUserRequest) {
        Long id = banUserRequest.getId();
        Boolean b = userService.banUser(id);
        if (!b){
            return Result.error(ErrorCode.SYSTEM_ERROR, "封禁失败");
        }
        return Result.success("封禁成功");
    }
}
