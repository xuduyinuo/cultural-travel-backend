package com.xudu.culturaltravelbackend.model.dto.elementdto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName element
 */

@Data
@ApiModel(description = "添加元素参数")
public class AddElementRequest implements Serializable {

    /**
     * 元素名称
     */
    @ApiModelProperty(value = "元素名称", required = true)
    private String elementName;

    /**
     * 元素图片
     */
    @ApiModelProperty(value = "元素图片", required = true)
    private MultipartFile elementImage;

    /**
     * 对应标签id
     */
    @ApiModelProperty(value = "对应标签id", required = true)
    private Long tagId;

    private static final long serialVersionUID = 1L;
}