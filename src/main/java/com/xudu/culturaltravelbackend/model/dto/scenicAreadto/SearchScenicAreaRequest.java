package com.xudu.culturaltravelbackend.model.dto.scenicAreadto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xudu.culturaltravelbackend.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName scenic_area
 */

@Data
public class SearchScenicAreaRequest extends PageRequest implements Serializable {
    /**
     * 景区id
     */
    private Long id;

    /**
     * 景区名称
     */
    private String scenicAreaName;

    /**
     * 景区经度
     */
    private Double scenicAreaLongitude;

    /**
     * 景区纬度
     */
    private Double scenicAreaLatitude;


    private static final long serialVersionUID = 1L;
}