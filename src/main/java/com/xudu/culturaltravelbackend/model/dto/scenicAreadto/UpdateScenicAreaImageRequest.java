package com.xudu.culturaltravelbackend.model.dto.scenicAreadto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName scenic_area
 */

@Data
@ApiModel(description = "更新景区图片参数")
public class UpdateScenicAreaImageRequest implements Serializable {
    /**
     * 景区id
     */
    @ApiModelProperty(value = "景区id", required = true)
    private Long id;

    /**
     * 图片索引
     */
    @ApiModelProperty(value = "图片索引", required = true)
    private Long index;

    /**
     * 景区图片(多个图片的话转成json字符串存储)
     */
    @ApiModelProperty(value = "景区图片", required = true)
    private MultipartFile scenicAreaImage;


    private static final long serialVersionUID = 1L;
}