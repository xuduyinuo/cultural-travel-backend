package com.xudu.culturaltravelbackend.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @TableName route
 */

@Data
public class RouteVO implements Serializable {
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
     * 路线图片
     */
    private List<String> routeImage;

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
    private List<ScenicAreaVO> alongScenicAreaVO;

    /**
     * 路线包含的element
     */
    private List<ElementVO> alongElementVO;

    /**
     * 创建线路的用户id
     */
    private String createRouteUserAccount;

    /**
     * 线路审核状态 0-未审核 1-已审核
     */
    private Integer routeStatus;

    /**
     * 线路标签
     */
    private List<String> routeTags;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}