package com.xudu.culturaltravelbackend.model.dto.scenicSpotdto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName scenic_spot
 */

@Data
public class AddScenicSpotRequest implements Serializable {

    /**
     * 景点名称
     */
    private String scenicSpotName;

    /**
     * 景点图片(多个图片用json字符串表示)
     */
    private MultipartFile[] scenicSpotImages;

    /**
     * 景点详情
     */
    private String scenicSpotInfo;

    /**
     * 景区id
     */
    private Long scenicAreaId;


    private static final long serialVersionUID = 1L;
}