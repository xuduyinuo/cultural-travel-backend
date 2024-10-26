package com.xudu.culturaltravelbackend.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName tag
 */

@Data
public class TagVO implements Serializable {
    /**
     * 标签id
     */
    private Long id;

    /**
     * 标签名称
     */
    private String tagName;

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