package com.xudu.culturaltravelbackend.controller.impl;

import com.xudu.culturaltravelbackend.annotation.AuthCheck;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.constant.UserConstant;
import com.xudu.culturaltravelbackend.controller.ScenicAreaController;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.AddScenicAreaRequest;
import com.xudu.culturaltravelbackend.service.ScenicAreaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className: ScenicAreaControllerImpl
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-26
 */
@CrossOrigin
@RequestMapping("/scenicArea")
@RestController
public class ScenicAreaControllerImpl implements ScenicAreaController {


    @Resource
    private ScenicAreaService scenicAreaService;

    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @Override
    public Result addScenicArea(AddScenicAreaRequest addScenicAreaRequest) {
        Long id = scenicAreaService.saveScenicArea(addScenicAreaRequest);
        return Result.success(id);
    }
}
