package com.xudu.culturaltravelbackend.model.dto.elementdto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName element
 */

@Data
public class SearchElementRequest implements Serializable {
    /**
     * 元素id
     */
    private Long id;

    /**
     * 元素名称
     */
    private String elementName;

    /**
     * 对应标签id
     */
    private Long tagId;


    private static final long serialVersionUID = 1L;
}