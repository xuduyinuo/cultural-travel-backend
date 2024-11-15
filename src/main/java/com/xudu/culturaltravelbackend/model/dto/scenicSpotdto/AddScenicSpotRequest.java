package com.xudu.culturaltravelbackend.model.dto.scenicSpotdto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName scenic_spot
 */

@Data
@ApiModel(description = "添加景点参数")
public class AddScenicSpotRequest implements Serializable {

    /**
     * 景点名称
     */
    @ApiModelProperty(value = "景点名称", required = true)
    private String scenicSpotName;

    /**
     * 景点图片(多个图片用json字符串表示)
     */
    @ApiModelProperty(value = "景点图片(多个图片用json字符串表示)", required = true)
    private MultipartFile[] scenicSpotImages;

    /**
     * 景点详情
     */
    @ApiModelProperty(value = "景点详情", required = true)
    private String scenicSpotInfo;

    /**
     * 景区id
     */
    @ApiModelProperty(value = "景区id", required = true)
    private Long scenicAreaId;


    private static final long serialVersionUID = 1L;
}