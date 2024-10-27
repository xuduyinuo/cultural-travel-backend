package com.xudu.culturaltravelbackend.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.exception.ServiceException;
import com.xudu.culturaltravelbackend.model.entity.ScenicArea;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName DeleteUtil
 * @Description
 * @Author xudu
 * @Time 2024/10/27 10:40
 */
@Component
public class DeleteUtil {

    public static void checkId(List<Long> ids) {
        //校验id是否合法
        if (CollectionUtils.isEmpty(ids)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        ids.forEach(id -> {
            if (id <= 0) {
                throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
            }
        });
    }

}
