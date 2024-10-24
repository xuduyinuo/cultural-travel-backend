package com.xudu.culturaltravelbackend.common;

import lombok.Data;

/**
 * 通用分页请求
 */
@Data
public class PageRequest {

    /**
     * 页面大小
     */
    protected int pageSize = 10;

    /**
     * 当前是第几页
     */
    protected int pageNum = 1;
}
