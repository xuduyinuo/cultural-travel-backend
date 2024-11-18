package com.xudu.culturaltravelbackend.model.dto.routedto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName route
 */

@Data
@ApiModel(description = "更新路线图片参数")
public class UpdateRouteImageRequest implements Serializable {
    /**
     * 路线id
     */
    @ApiModelProperty(value = "路线id", required = true)
    private Long id;

    /**
     * 图片序号
     */
    @ApiModelProperty(value = "图片索引", required = true)
    private Long index;

    /**
     * 路线图片(多个图片用json字符串表示)
     */
    @ApiModelProperty(value = "路线图片", required = true)
    //private String routeImageUrl;
    private MultipartFile routeImage;

    private static final long serialVersionUID = 1L;
}