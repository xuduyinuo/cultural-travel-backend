package com.xudu.culturaltravelbackend.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName element
 */
@Data
public class ElementVO implements Serializable {
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
    private String elementImage;

    /**
     * 对应标签id
     */
    private Long tagId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}