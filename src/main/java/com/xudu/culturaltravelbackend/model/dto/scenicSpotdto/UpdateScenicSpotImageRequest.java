package com.xudu.culturaltravelbackend.model.dto.scenicSpotdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 
 * @TableName scenic_area
 */

@Data
@ApiModel(description = "更新景点图片参数")
public class UpdateScenicSpotImageRequest implements Serializable {
    /**
     * 景点id
     */
    @ApiModelProperty(value = "景点id", required = true)
    private Long id;

    /**
     * 图片索引
     */
    @ApiModelProperty(value = "图片索引", required = true)
    private Long index;

    /**
     * 景点图片
     */
    @ApiModelProperty(value = "景点图片", required = true)
    private MultipartFile scenicSpotImage;


    private static final long serialVersionUID = 1L;
}