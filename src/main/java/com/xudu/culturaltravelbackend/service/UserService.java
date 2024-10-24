package com.xudu.culturaltravelbackend.service;

import com.xudu.culturaltravelbackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xudu.culturaltravelbackend.model.vo.UserVO;

/**
* @author xudu
* @description 针对表【user】的数据库操作Service
* @createDate 2024-10-10 22:53:56
*/
public interface UserService extends IService<User> {

    /**
     * 注册用户
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 确认密码
     * @return 注册成功的用户id
     */
    Long RegisterUser(String userAccount, String userPassword, String checkPassword);

    /**
     * 登录用户
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */
    UserVO LoginUser(String userAccount, String userPassword);
}
