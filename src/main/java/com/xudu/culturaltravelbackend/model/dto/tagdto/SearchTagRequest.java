package com.xudu.culturaltravelbackend.model.dto.tagdto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@ApiModel(description = "搜索标签参数")
public class SearchTagRequest implements Serializable {
    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id", required = false)
    private Long id;

    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称", required = false)
    private String tagName;


    private static final long serialVersionUID = 1L;
}