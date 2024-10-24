package com.xudu.culturaltravelbackend.model.dto.tagdto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName tag
 */
@Data
public class AddTagRequest implements Serializable {


    /**
     * 标签名称
     */
    private String tagName;


    private static final long serialVersionUID = 1L;
}