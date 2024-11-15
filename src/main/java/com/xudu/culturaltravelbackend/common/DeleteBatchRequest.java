package com.xudu.culturaltravelbackend.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @className: DeleteRequest
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-24
 */
@Data
@ApiModel(description = "批量删除参数")
public class DeleteBatchRequest {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "批量删除id列表", required = false)
    private List<Long> ids;
}
