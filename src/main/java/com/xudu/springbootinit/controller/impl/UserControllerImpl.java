package com.xudu.springbootinit.controller.impl;

import com.xudu.springbootinit.common.Result;
import com.xudu.springbootinit.controller.UserController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: UserControllerImpl
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-10
 */
@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    @Override
    public Result hello() {
        return Result.success(10);
    }
}
