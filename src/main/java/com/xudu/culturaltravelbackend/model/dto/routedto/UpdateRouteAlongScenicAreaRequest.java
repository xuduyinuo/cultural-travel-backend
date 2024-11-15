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
@ApiModel(description = "添加路线沿途景区参数")
public class UpdateRouteAlongScenicAreaRequest implements Serializable {

    /**
     * 路线id
     */
    @ApiModelProperty(value = "路线id", required = true)
    private Long id;

    /**
     * 路线图片(多个图片用json字符串表示)
     */
    @ApiModelProperty(value = "沿途景区id", required = false)
    private List<Long> routeAlongScenicArea;


    private static final long serialVersionUID = 1L;
}