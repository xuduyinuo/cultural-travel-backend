package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.AddScenicAreaRequest;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @className: ScenicAreaController
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-26
 */
public interface ScenicAreaController {

    @PostMapping("/add")
    public Result addScenicArea(AddScenicAreaRequest addScenicAreaRequest);

}
