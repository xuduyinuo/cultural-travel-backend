package com.xudu.culturaltravelbackend.model.dto.scenicAreadto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class UpdateScenicAreaRequest implements Serializable {
    /**
     * 景区id
     */
    private Long id;

    /**
     * 景区名称
     */
    private String scenicAreaName;

    /**
     * 景区信息（300字以内）
     */
    private String scenicAreaInfo;

    /**
     * 景区经度
     */
    private Double scenicAreaLongitude;

    /**
     * 景区纬度
     */
    private Double scenicAreaLatitude;

    /**
     * 景区图片(多个图片的话转成json字符串存储)
     */
    // private String scenicAreaImage;

    /**
     * 景区语音介绍
     */
    private MultipartFile scenicAreaVoice;



    private static final long serialVersionUID = 1L;
}