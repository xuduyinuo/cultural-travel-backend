package com.xudu.culturaltravelbackend.controller.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xudu.culturaltravelbackend.annotation.AuthCheck;
import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.constant.UserConstant;
import com.xudu.culturaltravelbackend.controller.ScenicAreaController;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.*;
import com.xudu.culturaltravelbackend.model.vo.ScenicAreaVO;
import com.xudu.culturaltravelbackend.service.ScenicAreaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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


    @Override
    public Result addScenicArea(AddScenicAreaRequest addScenicAreaRequest) {
        Long id = scenicAreaService.saveScenicArea(addScenicAreaRequest);
        return Result.success(id);
    }

    @Override
    public Result searchScenicAresListByPage(SearchScenicAreaRequest searchScenicAreaRequest) {
        Page<ScenicAreaVO> scenicAreaVOPage = scenicAreaService.searchScenicAreaListByPage(searchScenicAreaRequest);
        return Result.success(scenicAreaVOPage);
    }

    @Override
    public Result deleteScenicArea(@RequestBody DeleteBatchRequest deleteBatchRequest) {
        List<Long> ids = deleteBatchRequest.getIds();
        Integer i = scenicAreaService.deleteScenicArea(ids);
        return Result.success(i);
    }

    @Override
    public Result updateScenicArea(UpdateScenicAreaRequest updateScenicAreaRequest) {
        Boolean b = scenicAreaService.updateScenicArea(updateScenicAreaRequest);

        return Result.success(b);
    }

    @Override
    public Result updateScenicAreaImage(UpdateScenicAreaImageRequest updateScenicAreaImageRequest) {
        Boolean b = scenicAreaService.updateScenicAreaImage(updateScenicAreaImageRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR, "更新失败");
        }
        return Result.success("更新成功");
    }

    @Override
    public Result addScenicAreaImage(AddScenicAreaImageRequest addScenicAreaImageRequest) {
        Boolean b = scenicAreaService.addScenicAreaImage(addScenicAreaImageRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR, "添加失败");
        }
        return Result.success("添加成功");
    }

    @Override
    public Result deleteScenicAreaImage(DeleteScenicAreaImageRequest deleteScenicAreaImageRequest) {
        Boolean b = scenicAreaService.deleteScenicAreaImage(deleteScenicAreaImageRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR, "删除失败");
        }
        return Result.success("删除成功");    }
}
