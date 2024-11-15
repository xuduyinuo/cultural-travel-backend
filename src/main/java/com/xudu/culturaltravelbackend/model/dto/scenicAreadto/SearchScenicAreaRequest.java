package com.xudu.culturaltravelbackend.model.dto.scenicAreadto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xudu.culturaltravelbackend.common.PageRequest;
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
@ApiModel(description = "搜索景区参数")
public class SearchScenicAreaRequest extends PageRequest implements Serializable {
    /**
     * 景区id
     */
    @ApiModelProperty(value = "景区id", required = false)
    private Long id;

    /**
     * 景区名称
     */
    @ApiModelProperty(value = "景区名称", required = false)
    private String scenicAreaName;

    /**
     * 景区经度
     */
    @ApiModelProperty(value = "景区经度", required = false)
    private Double scenicAreaLongitude;

    /**
     * 景区纬度
     */
    @ApiModelProperty(value = "景区纬度", required = false)
    private Double scenicAreaLatitude;


    private static final long serialVersionUID = 1L;
}