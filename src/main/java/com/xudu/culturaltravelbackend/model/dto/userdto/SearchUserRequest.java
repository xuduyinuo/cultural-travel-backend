package com.xudu.culturaltravelbackend.model.dto.userdto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xudu.culturaltravelbackend.common.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "搜索用户参数")
public class SearchUserRequest extends PageRequest implements Serializable {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = false)
    private Long id;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号", required = false)
    private String userAccount;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", required = false)
    private String userName;

    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户电话", required = false)
    private String userPhone;

    /**
     * 用户角色 o-普通用户 1-管理员
     */
    @ApiModelProperty(value = "用户角色 o-普通用户 1-管理员", required = false)
    private Integer userRole;

    /**
     * 用户标签
     */
    @ApiModelProperty(value = "用户标签", required = false)
    private String userTags;


    private static final long serialVersionUID = 1L;
}