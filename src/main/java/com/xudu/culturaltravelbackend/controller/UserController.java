package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.model.dto.userdto.LoginRequest;
import com.xudu.culturaltravelbackend.model.dto.userdto.RegisterRequest;
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


    @PostMapping("/register")
    Result registerUser(@RequestBody RegisterRequest registerRequest);

    @PostMapping("/login")
    Result loginUser(@RequestBody LoginRequest loginRequest);
}
