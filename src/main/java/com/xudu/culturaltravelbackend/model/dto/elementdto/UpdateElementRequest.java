package com.xudu.culturaltravelbackend.model.dto.elementdto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.nio.channels.MulticastChannel;
import java.time.LocalDateTime;

/**
 * 
 * @TableName element
 */
@Data
@ApiModel(description = "更新元素参数")
public class UpdateElementRequest implements Serializable {
    /**
     * 元素id
     */
    @ApiModelProperty(value = "元素id", required = false)
    private Long id;

    /**
     * 元素名称
     */
    @ApiModelProperty(value = "元素名称", required = false)
    private String elementName;

    /**
     * 元素图片
     */
    @ApiModelProperty(value = "元素图片", required = false)
    private MultipartFile elementImage;

    /**
     * 对应标签id
     */
    private Long tagId;

    private static final long serialVersionUID = 1L;
}