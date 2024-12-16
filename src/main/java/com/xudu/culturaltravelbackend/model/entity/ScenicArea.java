package com.xudu.culturaltravelbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName scenic_area
 */
@TableName(value ="scenic_area")
@Data
public class ScenicArea implements Serializable {
    /**
     * 景区id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 景区名称
     */
    private String scenicAreaName;

    /**
     * 景区信息（300字以内）
     */
    private String scenicAreaInfo;

    /**
     * 景区经度
     */
    private Double scenicAreaLongitude;

    /**
     * 景区纬度
     */
    private Double scenicAreaLatitude;

    /**
     * 景区图片(多个图片的话转成json字符串存储)
     */
    private String scenicAreaImage;

    /**
     * 景区语音介绍
     */
    private String scenicAreaVoice;

    /**
     * 景区审核状态 0-未审核 1-已审核
     */
    private Integer scenicAreaStatus;

    /**
     * 创建人id
     */
    private Long createScenicAreaUserId;

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