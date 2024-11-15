package com.xudu.culturaltravelbackend.model.dto.userdto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
@ApiModel(description = "更新用户参数")
public class UpdateUserRequest implements Serializable {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    private Long id;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号", required = false)
    private String userAccount;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", required = false)
    private String userPassword;

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
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", required = false)
    private MultipartFile userImage;

    /**
     * 用户角色 o-普通用户 1-管理员
     */
    @ApiModelProperty(value = "用户角色 o-普通用户 1-管理员", required = false)
    private Integer userRole;

    /**
     * 用户标签
     */
    @ApiModelProperty(value = "用户标签", required = false)
    private List<String> userTags;


    private static final long serialVersionUID = 1L;
}