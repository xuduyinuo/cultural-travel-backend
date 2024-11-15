package com.xudu.culturaltravelbackend.model.dto.routedto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @TableName route
 */
@Data
@ApiModel(description = "更新路线参数")
public class UpdateRouteRequest implements Serializable {

    /**
     * 路线id
     */
    @ApiModelProperty(value = "路线id", required = true)
    private Long id;

    /**
     * 路线名称
     */
    @ApiModelProperty(value = "路线名称", required = false)
    private String routeName;

    /**
     * 路线详情
     */
    @ApiModelProperty(value = "路线详情", required = false)
    private String routeInfo;

    /**
     * 路线图片(多个图片用json字符串表示)
     */
    //private String routeImage;

    /**
     * 路线里程
     */
    @ApiModelProperty(value = "路线里程", required = false)
    private Integer routeMileage;

    /**
     * 花费时间
     */
    @ApiModelProperty(value = "花费时间", required = false)
    private Integer spendTime;

    /**
     * 适宜时间
     */
    @ApiModelProperty(value = "适宜时间", required = false)
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
    @ApiModelProperty(value = "路线标签", required = false)
    private List<String> routeTags;


    private static final long serialVersionUID = 1L;
}