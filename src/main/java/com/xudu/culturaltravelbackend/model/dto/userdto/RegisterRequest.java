package com.xudu.culturaltravelbackend.model.dto.userdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: RegisterRequest
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-24
 */
@Data
@ApiModel(description = "注册请求参数")
public class RegisterRequest {
    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号", required = true)
    private String userAccount;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", required = true)
    private String userPassword;

    /**
     * 校验密码
     */
    @ApiModelProperty(value = "校验密码", required = true)
    private String checkPassword;
}
