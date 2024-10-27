package com.xudu.culturaltravelbackend.model.dto.scenicSpotdto;

import com.baomidou.mybatisplus.annotation.*;
import com.xudu.culturaltravelbackend.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName scenic_spot
 */

@Data
public class SearchScenicSpotRequest extends PageRequest implements Serializable {
    /**
     * 景点id
     */
    private Long id;

    /**
     * 景点名称
     */
    private String scenicSpotName;

    /**
     * 景区id
     */
    private Long scenicAreaId;

    private static final long serialVersionUID = 1L;
}