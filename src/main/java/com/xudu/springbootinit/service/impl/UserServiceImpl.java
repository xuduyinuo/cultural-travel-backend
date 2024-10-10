package com.xudu.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xudu.springbootinit.model.entity.User;
import com.xudu.springbootinit.service.UserService;
import com.xudu.springbootinit.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author xudu
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-10-10 22:53:56
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




