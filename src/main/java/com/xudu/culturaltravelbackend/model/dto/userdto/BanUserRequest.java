package com.xudu.culturaltravelbackend.model.dto.userdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName BanUserRequest
 * @Description
 * @Author xudu
 * @Time 2024/12/2 10:05
 */
@ApiModel(description = "封禁用户请求参数")
@Data
public class BanUserRequest {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    private Long id;

}
