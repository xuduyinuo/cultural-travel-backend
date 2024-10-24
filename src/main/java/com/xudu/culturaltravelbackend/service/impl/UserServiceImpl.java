package com.xudu.culturaltravelbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.exception.ServiceException;
import com.xudu.culturaltravelbackend.model.entity.User;
import com.xudu.culturaltravelbackend.model.vo.UserVO;
import com.xudu.culturaltravelbackend.service.UserService;
import com.xudu.culturaltravelbackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author xudu
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-10-10 22:53:56
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Override
    public Long RegisterUser(String userAccount, String userPassword, String checkPassword) {
        if (!userPassword.equals(checkPassword)){
            throw new ServiceException(ErrorCode.PARAMS_ERROR);
        }
        //QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.lambda().eq(User::getUserAccount, userAccount).eq(User::getUserPassword, userPassword);
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(userPassword);
        this.save(user);
        return user.getId();

    }

    @Override
    public UserVO LoginUser(String userAccount, String userPassword) {
        return null;
    }
}




