package com.xudu.culturaltravelbackend.common;

import lombok.Data;

import java.util.List;

/**
 * @className: DeleteRequest
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-24
 */
@Data
public class DeleteBatchRequest {

    /**
     * 用户id
     */
    private List<Long> ids;
}
