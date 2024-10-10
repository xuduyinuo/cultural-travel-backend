package com.xudu.springbootinit.controller;

import com.xudu.springbootinit.common.Result;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: UserController
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-10
 */
public interface UserController {

    @GetMapping("/hello")
    Result hello();
}
