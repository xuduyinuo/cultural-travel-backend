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
public class UpdateRouteImageRequest implements Serializable {
    /**
     * 路线id
     */
    private Long id;

    /**
     * 图片序号
     */
    private Long index;

    /**
     * 路线图片(多个图片用json字符串表示)
     */
    private String routeImageUrl;


    private static final long serialVersionUID = 1L;
}