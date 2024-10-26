package com.xudu.culturaltravelbackend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @className: UserVO
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-24
 */
@Data
public class UserVO implements Serializable {

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
     * 用户头像
     */
    private String userImage;

    /**
     * 用户角色 o-普通用户 1-管理员
     */
    private Integer userRole;

    /**
     * 用户标签
     */
    private String userTags;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 后端生成的token
     */
    private String token;


    private static final long serialVersionUID = 1L;
}
