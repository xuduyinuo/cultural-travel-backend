package com.xudu.culturaltravelbackend.model.dto.elementdto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName element
 */

@Data
@ApiModel(description = "搜索元素参数")
public class SearchElementRequest implements Serializable {
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
     * 对应标签id
     */
    @ApiModelProperty(value = "对应标签id", required = false)
    private Long tagId;


    private static final long serialVersionUID = 1L;
}