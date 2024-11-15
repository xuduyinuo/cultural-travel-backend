package com.xudu.culturaltravelbackend.model.dto.scenicAreadto;

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
 * @TableName scenic_area
 */

@Data
@ApiModel(description = "删除景区图片参数")
public class DeleteScenicAreaImageRequest implements Serializable {

    /**
     * 景区id
     */
    @ApiModelProperty(value = "景区id", required = true)
    private Long id;

    /**
     * 要删除的图片索引
     */
    @ApiModelProperty(value = "图片索引", required = true)
    private Long index;

    private static final long serialVersionUID = 1L;
}