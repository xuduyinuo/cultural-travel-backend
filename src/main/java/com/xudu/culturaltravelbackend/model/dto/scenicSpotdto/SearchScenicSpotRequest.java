package com.xudu.culturaltravelbackend.model.dto.scenicSpotdto;

import com.baomidou.mybatisplus.annotation.*;
import com.xudu.culturaltravelbackend.common.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName scenic_spot
 */

@Data
@ApiModel(description = "搜索景点参数")
public class SearchScenicSpotRequest extends PageRequest implements Serializable {
    /**
     * 景点id
     */
    @ApiModelProperty(value = "景点id", required = false)
    private Long id;

    /**
     * 景点名称
     */
    @ApiModelProperty(value = "景点名称", required = false)
    private String scenicSpotName;

    /**
     * 景区id
     */
    @ApiModelProperty(value = "景区id", required = false)
    private Long scenicAreaId;

    private static final long serialVersionUID = 1L;
}