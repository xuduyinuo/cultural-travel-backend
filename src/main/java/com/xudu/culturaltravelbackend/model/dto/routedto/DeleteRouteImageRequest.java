package com.xudu.culturaltravelbackend.model.dto.routedto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 
 * @TableName route
 */

@Data
public class DeleteRouteImageRequest implements Serializable {
    /**
     * 路线id
     */

    private Long id;

    /**
     * 删除的图片下标
     */
    private Long index;

    private static final long serialVersionUID = 1L;
}