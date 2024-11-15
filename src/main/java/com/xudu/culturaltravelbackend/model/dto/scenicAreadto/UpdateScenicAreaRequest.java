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
import java.nio.channels.MulticastChannel;
import java.time.LocalDateTime;

/**
 * 
 * @TableName scenic_area
 */
@Data
@ApiModel(description = "更新景区参数")
public class UpdateScenicAreaRequest implements Serializable {
    /**
     * 景区id
     */
    @ApiModelProperty(value = "景区id", required = true)
    private Long id;

    /**
     * 景区名称
     */
    @ApiModelProperty(value = "景区名称", required = false)
    private String scenicAreaName;

    /**
     * 景区信息（300字以内）
     */
    @ApiModelProperty(value = "景区信息（300字以内）", required = false)
    private String scenicAreaInfo;

    /**
     * 景区经度
     */
    @ApiModelProperty(value = "景区经度", required = false)
    private Double scenicAreaLongitude;

    /**
     * 景区纬度
     */
    @ApiModelProperty(value = "景区纬度", required = false)
    private Double scenicAreaLatitude;

    /**
     * 景区图片(多个图片的话转成json字符串存储)
     */
    // private String scenicAreaImage;

    /**
     * 景区语音介绍
     */
    @ApiModelProperty(value = "景区语音介绍", required = false)
    private MultipartFile scenicAreaVoice;



    private static final long serialVersionUID = 1L;
}