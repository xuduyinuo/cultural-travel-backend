package com.xudu.culturaltravelbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName element
 */
@TableName(value ="element")
@Data
public class Element implements Serializable {
    /**
     * 元素id
     */
    @TableId(type = IdType.ASSIGN_ID)
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

    /**
     * 是否删除 0-存在 1-删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}