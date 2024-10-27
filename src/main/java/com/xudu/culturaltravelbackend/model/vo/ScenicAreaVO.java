package com.xudu.culturaltravelbackend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @TableName scenic_area
 */

@Data
public class ScenicAreaVO implements Serializable {
    /**
     * 景区id
     */
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
    private List<String> scenicAreaImages;

    /**
     * 景区语音介绍
     */
    private String scenicAreaVoice;

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