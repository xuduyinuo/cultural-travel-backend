package com.xudu.culturaltravelbackend.model.dto.routedto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 
 * @TableName route
 */

@Data
public class AddRouteImageRequest implements Serializable {
    /**
     * 路线id
     */

    private Long id;


    /**
     * 路线图片(多个图片用json字符串表示)
     */
    private MultipartFile routeImage;


    private static final long serialVersionUID = 1L;
}