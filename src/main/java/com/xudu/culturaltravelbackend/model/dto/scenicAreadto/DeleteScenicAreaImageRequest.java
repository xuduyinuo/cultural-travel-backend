package com.xudu.culturaltravelbackend.model.dto.scenicAreadto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName scenic_area
 */

@Data
public class DeleteScenicAreaImageRequest implements Serializable {

    /**
     * 景区id
     */
    private Long id;

    /**
     * 要删除的图片索引
     */
    private Long index;

    private static final long serialVersionUID = 1L;
}