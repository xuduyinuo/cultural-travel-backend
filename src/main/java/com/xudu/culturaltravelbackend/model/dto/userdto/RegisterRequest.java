package com.xudu.culturaltravelbackend.model.dto.userdto;

import lombok.Data;

/**
 * @className: RegisterRequest
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-24
 */
@Data
public class RegisterRequest {
    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 校验密码
     */
    private String checkPassword;
}
