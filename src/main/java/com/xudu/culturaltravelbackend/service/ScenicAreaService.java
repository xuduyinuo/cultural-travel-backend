package com.xudu.culturaltravelbackend.service;

import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.AddScenicAreaRequest;
import com.xudu.culturaltravelbackend.model.entity.ScenicArea;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author xudu
* @description 针对表【scenic_area】的数据库操作Service
* @createDate 2024-10-26 23:25:05
*/
public interface ScenicAreaService extends IService<ScenicArea> {

    /**
     * 新增景区
     * @param addScenicAreaRequest 添加景区请求参数
     * @return 新增景区id
     */
    public Long saveScenicArea(AddScenicAreaRequest addScenicAreaRequest);
}
