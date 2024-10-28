package com.xudu.culturaltravelbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName route
 */
@TableName(value ="route")
@Data
public class Route implements Serializable {
    /**
     * 路线id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 路线名称
     */
    private String routeName;

    /**
     * 路线详情
     */
    private String routeInfo;

    /**
     * 路线图片(多个图片用json字符串表示)
     */
    private String routeImage;

    /**
     * 路线里程
     */
    private Integer routeMileage;

    /**
     * 花费时间
     */
    private Integer spendTime;

    /**
     * 适宜时间
     */
    private String suitableTime;

    /**
     * 创建线路的用户id
     */
    private Long userId;

    /**
     * 线路审核状态 0-未审核 1-已审核
     */
    private Integer routeStatus;

    /**
     * 线路标签
     */
    private String routeTags;

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