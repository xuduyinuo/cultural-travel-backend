package com.xudu.culturaltravelbackend.model.dto.routedto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName route
 */
@Data
public class UpdateRouteRequest implements Serializable {

    /**
     * 路线id
     */
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
    //private String routeImage;

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
     * 沿途景区
     */


    /**
     * 创建线路的用户id--不能修改
     */
    //private Long userId;

    /**
     * 线路审核状态 0-未审核 1-已审核
     */
    //private Integer routeStatus;

    /**
     * 线路标签
     */
    //private String routeTags;


    private static final long serialVersionUID = 1L;
}