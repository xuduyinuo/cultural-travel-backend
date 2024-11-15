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
@ApiModel(description = "添加标签参数")
public class AddTagRequest implements Serializable {


    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称", required = true)
    private String tagName;


    private static final long serialVersionUID = 1L;
}