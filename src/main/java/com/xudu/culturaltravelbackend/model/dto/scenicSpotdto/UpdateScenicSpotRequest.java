package com.xudu.culturaltravelbackend.model.dto.scenicSpotdto;

import com.baomidou.mybatisplus.annotation.*;
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
@ApiModel(description = "更新景点参数")
public class UpdateScenicSpotRequest implements Serializable {
    /**
     * 景点id
     */
    @ApiModelProperty(value = "景点id", required = true)
    private Long id;

    /**
     * 景点名称
     */
    @ApiModelProperty(value = "景点名称", required = false)
    private String scenicSpotName;


    /**
     * 景点详情
     */
    @ApiModelProperty(value = "景点详情", required = false)
    private String scenicSpotInfo;

    /**
     * 景区id
     */
    @ApiModelProperty(value = "景区id", required = false)
    private Long scenicAreaId;


    private static final long serialVersionUID = 1L;
}