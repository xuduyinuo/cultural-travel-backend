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
@ApiModel(description = "删除路线图片参数")
public class DeleteRouteImageRequest implements Serializable {
    /**
     * 路线id
     */
    @ApiModelProperty(value = "路线id", required = true)
    private Long id;

    /**
     * 删除的图片下标
     */
    @ApiModelProperty(value = "图片索引", required = true)
    private Long imageIndex;

    private static final long serialVersionUID = 1L;
}