package com.xudu.culturaltravelbackend.model.dto.routedto;

import com.baomidou.mybatisplus.annotation.*;
import com.xudu.culturaltravelbackend.common.PageRequest;
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
@ApiModel(description = "搜索路线参数")
public class SearchRouteRequest extends PageRequest implements Serializable {
    /**
     * 路线id
     */
    @ApiModelProperty(value = "路线id", required = false)
    private Long id;

    /**
     * 路线名称
     */
    @ApiModelProperty(value = "路线名称", required = false)
    private String routeName;


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
     * 创建线路的用户id
     */
    @ApiModelProperty(value = "创建线路的用户id", required = false)
    private Long userId;

    /**
     * 线路审核状态 0-未审核 1-已审核
     */
    @ApiModelProperty(value = "线路审核状态 0-未审核 1-已审核", required = false)
    private Integer routeStatus;

    /**
     * 线路标签
     */
    @ApiModelProperty(value = "线路标签", required = false)
    private String routeTags;

    private static final long serialVersionUID = 1L;
}