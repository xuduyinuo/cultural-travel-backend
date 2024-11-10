package com.xudu.culturaltravelbackend.model.dto.userdto;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: LoginRequest
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-24
 */
@Data
public class AddUserTagsRequest implements Serializable {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 添加的标签
     */
    private String userTag;
}
