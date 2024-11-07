package com.xudu.culturaltravelbackend.model.dto.routedto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName route
 */

@Data
public class AddRouteRequest implements Serializable {


    /**
     * 路线名称
     */
    private String routeName;

    /**
     * 路线详情
     */
    private String routeInfo;

    /**
     * 路线图片url，多个图片用json字符串表示，例如：["xxx.png", "xxxx.jpg"]
     */
    private String routeImages;

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
     * 沿途景区,json景区id字符串
     */
    private String alongScenicArea;

    /**
     * 沿途element，json 元素id字符串
     */
    private String alongElement;

    /**
     * 线路标签例如：["xxx", "xxxx"]
     */
    private String routeTags;


    private static final long serialVersionUID = 1L;
}