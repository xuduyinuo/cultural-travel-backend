package com.xudu.culturaltravelbackend.model.dto.elementdto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.nio.channels.MulticastChannel;
import java.time.LocalDateTime;

/**
 * 
 * @TableName element
 */
@Data
public class UpdateElementRequest implements Serializable {
    /**
     * 元素id
     */
    private Long id;

    /**
     * 元素名称
     */
    private String elementName;

    /**
     * 元素图片
     */
    private MultipartFile elementImage;

    /**
     * 对应标签id
     */
    private Long tagId;

    private static final long serialVersionUID = 1L;
}