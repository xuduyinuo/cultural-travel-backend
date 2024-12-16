package com.xudu.culturaltravelbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName scenic_spot
 */
@TableName(value ="scenic_spot")
@Data
public class ScenicSpot implements Serializable {

    /**
     * 景点id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 景点名称
     */
    private String scenicSpotName;

    /**
     * 景点图片(多个图片用json字符串表示)
     */
    private String scenicSpotImage;

    /**
     * 景点详情
     */
    private String scenicSpotInfo;

    /**
     * 景区id
     */
    private Long scenicAreaId;

    /**
     * 景点审核状态 0-未审核 1-已审核
     */
    private Integer scenicSpotStatus;

    /**
     * 创建景点用户id
     */
    private Long createScenicSpotUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除 0-存在 1-删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}