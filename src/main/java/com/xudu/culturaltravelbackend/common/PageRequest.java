package com.xudu.culturaltravelbackend.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用分页请求
 */
@Data
@ApiModel(description = "分页参数")
public class PageRequest {

    /**
     * 页面大小
     */
    @ApiModelProperty(value = "页面大小", required = false)
    protected int pageSize = 10;

    /**
     * 当前是第几页
     */
    @ApiModelProperty(value = "页码", required = false)
    protected int pageNum = 1;
}
