package com.xudu.culturaltravelbackend.model.dto.userdto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xudu.culturaltravelbackend.common.PageRequest;
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
public class SearchUserRequest extends PageRequest implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户电话
     */
    private String userPhone;

    /**
     * 用户角色 o-普通用户 1-管理员
     */
    private Integer userRole;

    /**
     * 用户标签
     */
    private String userTags;


    private static final long serialVersionUID = 1L;
}