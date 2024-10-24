package com.xudu.culturaltravelbackend.model.dto.tagdto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName tag
 */

@Data
public class UpdateTagRequest implements Serializable {
    /**
     * 标签id
     */
    private Long id;

    /**
     * 标签名称
     */
    private String tagName;


    private static final long serialVersionUID = 1L;
}