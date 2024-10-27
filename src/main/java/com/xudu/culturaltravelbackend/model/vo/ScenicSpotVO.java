package com.xudu.culturaltravelbackend.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @TableName scenic_spot
 */

@Data
public class ScenicSpotVO implements Serializable {
    /**
     * 景点id
     */
    private Long id;

    /**
     * 景点名称
     */
    private String scenicSpotName;

    /**
     * 景点图片(多个图片用json字符串表示)
     */
    private List<String> scenicSpotImages;

    /**
     * 景点详情
     */
    private String scenicSpotInfo;

    /**
     * 景区id
     */
    private Long scenicAreaId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    private static final long serialVersionUID = 1L;
}