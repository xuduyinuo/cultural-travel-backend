package com.xudu.culturaltravelbackend.model.dto.tagdto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName tag
 */

@Data
@ApiModel(description = "更新标签参数")
public class UpdateTagRequest implements Serializable {
    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id", required = true)
    private Long id;

    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称", required = true)
    private String tagName;


    private static final long serialVersionUID = 1L;
}