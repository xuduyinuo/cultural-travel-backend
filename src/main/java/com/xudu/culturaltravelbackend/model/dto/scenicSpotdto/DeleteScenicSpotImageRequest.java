package com.xudu.culturaltravelbackend.model.dto.scenicSpotdto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 
 * @TableName scenic_area
 */

@Data
public class DeleteScenicSpotImageRequest implements Serializable {

    /**
     * 景点id
     */
    private Long id;

    /**
     * 图片索引
     */
    private Long index;


    private static final long serialVersionUID = 1L;
}