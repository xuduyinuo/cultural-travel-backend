package com.xudu.culturaltravelbackend.model.dto.scenicAreadto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @ClassName AddScenicAreaImageRequest
 * @Description
 * @Author xudu
 * @Time 2024/10/27 10:09
 */
@Data
@ApiModel(description = "添加景区图片参数")
public class AddScenicAreaImageRequest implements Serializable {

    /**
     * 景区id
     */
    @ApiModelProperty(value = "景区id", required = true)
    private Long id;


    /**
     * 添加的景区图片(多个图片的话转成json字符串存储)
     */
    @ApiModelProperty(value = "景区图片", required = true)
    private MultipartFile scenicAreaImage;


    private static final long serialVersionUID = 1L;
}
