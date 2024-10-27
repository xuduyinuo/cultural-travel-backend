package com.xudu.culturaltravelbackend.model.dto.scenicSpotdto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 
 * @TableName scenic_area
 */

@Data
public class UpdateScenicSpotImageRequest implements Serializable {
    /**
     * 景点id
     */
    private Long id;

    /**
     * 图片索引
     */
    private Long index;

    /**
     * 景点图片(多个图片的话转成json字符串存储)
     */
    private MultipartFile scenicSpotImage;


    private static final long serialVersionUID = 1L;
}