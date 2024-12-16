package com.xudu.culturaltravelbackend.model.dto.routedto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @TableName route
 */

@Data
@ApiModel(description = "添加路线参数")
public class AddRouteRequest implements Serializable {


    /**
     * 路线名称
     */
    @ApiModelProperty(value = "路线名称", required = true)
    private String routeName;

    /**
     * 路线详情
     */
    @ApiModelProperty(value = "路线详情", required = true)
    private String routeInfo;

    /**
     * 路线图片url，多个图片用json字符串表示，例如：["xxx.png", "xxxx.jpg"]
     */
    @ApiModelProperty(value = "路线图片url，多个图片用json字符串表示，例如：[\"xxx.png\", \"xxxx.jpg\"]", required = true)
    //private List<String> routeImages;
    private MultipartFile[] routeImages;

    /**
     * 路线里程
     */
    @ApiModelProperty(value = "路线里程", required = true)
    private Integer routeMileage;

    /**
     * 花费时间
     */
    @ApiModelProperty(value = "花费时间", required = true)
    private Integer spendTime;

    /**
     * 适宜时间
     */
    @ApiModelProperty(value = "适宜时间", required = true)
    private String suitableTime;

    /**
     * 沿途景区,json景区id字符串
     */
    @ApiModelProperty(value = "沿途景区,json景区id字符串", required = true)
    private List<Long> alongScenicArea;

    /**
     * 沿途element，json 元素id字符串
     */
    @ApiModelProperty(value = "沿途element，json 元素id字符串", required = true)
    private List<Long> routeElements;

    /**
     * 线路标签例如：["xxx", "xxxx"]
     */
    @ApiModelProperty(value = "线路标签例如：[\"xxx\", \"xxxx\"]", required = true)
    private List<String> routeTags;


    private static final long serialVersionUID = 1L;
}