package com.xudu.culturaltravelbackend.model.dto.userdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: LoginRequest
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-24
 */
@Data
@ApiModel(description = "添加用户标签参数")
public class AddUserTagsRequest implements Serializable {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    private Long id;

    /**
     * 添加的标签
     */
    @ApiModelProperty(value = "添加的标签", required = true)
    private String userTag;
}
