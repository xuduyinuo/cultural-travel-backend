package com.xudu.culturaltravelbackend.model.dto.scenicSpotdto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName scenic_spot
 */

@Data
public class UpdateScenicSpotRequest implements Serializable {
    /**
     * 景点id
     */
    private Long id;

    /**
     * 景点名称
     */
    private String scenicSpotName;


    /**
     * 景点详情
     */
    private String scenicSpotInfo;

    /**
     * 景区id
     */
    private Long scenicAreaId;


    private static final long serialVersionUID = 1L;
}