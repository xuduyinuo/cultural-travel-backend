package com.xudu.culturaltravelbackend.model.dto.routedto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 
 * @TableName route
 */

@Data
@ApiModel(description = "添加路线图片参数")
public class AddRouteImageRequest implements Serializable {
    /**
     * 路线id
     */
    @ApiModelProperty(value = "路线id", required = true)
    private Long id;


    /**
     * 路线图片(多个图片用json字符串表示)
     */
    @ApiModelProperty(value = "路线图片", required = true)
    private String routeImageUrl;


    private static final long serialVersionUID = 1L;
}