package com.xudu.culturaltravelbackend.model.dto.routedto;

import com.xudu.culturaltravelbackend.common.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName route
 */

@Data
@ApiModel(description = "搜索路线参数")
public class SearchRouteForMIniprogramRequest implements Serializable {
    /**
     * 路线id
     */
    @ApiModelProperty(value = "路线id", required = false)
    private Long id;



    private static final long serialVersionUID = 1L;
}