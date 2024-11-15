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
@ApiModel(description = "删除用户标签参数")
public class DeleteUserTagsRequest implements Serializable {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    private Long id;

    /**
     * 标签索引
     */
    @ApiModelProperty(value = "标签索引", required = true)
    private Long index;


}
