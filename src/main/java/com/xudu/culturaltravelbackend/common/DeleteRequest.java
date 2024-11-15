package com.xudu.culturaltravelbackend.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: DeleteRequest
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-24
 */
@Data
@ApiModel(description = "删除参数")
public class DeleteRequest {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "删除id", required = false)
    private Long id;
}
