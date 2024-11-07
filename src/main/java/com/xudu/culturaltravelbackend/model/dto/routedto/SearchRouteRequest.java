package com.xudu.culturaltravelbackend.model.dto.routedto;

import com.baomidou.mybatisplus.annotation.*;
import com.xudu.culturaltravelbackend.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @TableName route
 */

@Data
public class SearchRouteRequest extends PageRequest implements Serializable {
    /**
     * 路线id
     */
    private Long id;

    /**
     * 路线名称
     */
    private String routeName;


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

    private static final long serialVersionUID = 1L;
}