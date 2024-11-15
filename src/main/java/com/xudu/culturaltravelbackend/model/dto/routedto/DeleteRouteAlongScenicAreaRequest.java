package com.xudu.culturaltravelbackend.model.dto.routedto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @TableName route
 */

@Data
@ApiModel(description = "删除路线沿途景区参数")
public class DeleteRouteAlongScenicAreaRequest implements Serializable {

    /**
     * 路线id
     */
    @ApiModelProperty(value = "路线id", required = true)
    private Long id;

    /**
     * 路线图片(多个图片用json字符串表示)
     */
    @ApiModelProperty(value = "沿途景区索引", required = true)
    private Long index;


    private static final long serialVersionUID = 1L;
}