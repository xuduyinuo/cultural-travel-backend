package com.xudu.culturaltravelbackend.model.dto.scenicAreadto;

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
public class AddScenicAreaImageRequest implements Serializable {

    /**
     * 景区id
     */
    private Long id;


    /**
     * 添加的景区图片(多个图片的话转成json字符串存储)
     */
    private MultipartFile scenicAreaImage;


    private static final long serialVersionUID = 1L;
}
