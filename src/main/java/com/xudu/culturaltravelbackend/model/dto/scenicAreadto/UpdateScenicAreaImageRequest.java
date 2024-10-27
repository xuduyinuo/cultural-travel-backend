package com.xudu.culturaltravelbackend.model.dto.scenicAreadto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName scenic_area
 */

@Data
public class UpdateScenicAreaImageRequest implements Serializable {
    /**
     * 景区id
     */
    private Long id;

    /**
     * 图片索引
     */
    private Long index;

    /**
     * 景区图片(多个图片的话转成json字符串存储)
     */
    private MultipartFile scenicAreaImage;


    private static final long serialVersionUID = 1L;
}