package com.xudu.culturaltravelbackend.controller.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.controller.ScenicSpotController;
import com.xudu.culturaltravelbackend.model.dto.scenicSpotdto.*;
import com.xudu.culturaltravelbackend.model.vo.ScenicSpotVO;
import com.xudu.culturaltravelbackend.service.ScenicSpotService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ScenicSpotControllerImpl
 * @Description
 * @Author xudu
 * @Time 2024/10/27 15:17
 */
@CrossOrigin
@RequestMapping("/scenicSpot")
@RestController
public class ScenicSpotControllerImpl implements ScenicSpotController {


    @Resource
    private ScenicSpotService scenicSpotService;

    @Override
    public Result addScenicSpot(AddScenicSpotRequest addScenicSpotRequest) {
        Long id = scenicSpotService.saveScenicSpot(addScenicSpotRequest);
        return Result.success(id);
    }

    @Override
    public Result searchScenicSpotListByPage(SearchScenicSpotRequest searchScenicSpotRequest) {
        Page<ScenicSpotVO> scenicSpotVOPage = scenicSpotService.searchScenicSpotListByPage(searchScenicSpotRequest);
        return Result.success(scenicSpotVOPage);
    }

    @Override
    public Result deleteScenicSpot(DeleteBatchRequest deleteBatchRequest) {
        List<Long> ids = deleteBatchRequest.getIds();
        return Result.success(scenicSpotService.deleteScenicSpot(ids));
    }

    @Override
    public Result updateScenicSpot(UpdateScenicSpotRequest updateScenicSpotRequest) {
        Boolean b = scenicSpotService.updateScenicSpot(updateScenicSpotRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR,"更新失败");
        }
        return Result.success("更新成功");
    }

    @Override
    public Result updateScenicSpotImage(UpdateScenicSpotImageRequest updateScenicSpotImageRequest) {
        Boolean b = scenicSpotService.updateScenicSpotImage(updateScenicSpotImageRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR, "更新失败");
        }
        return Result.success("更新成功");
    }

    @Override
    public Result addScenicSpotImage(AddScenicSpotImageRequest addScenicSpotImageRequest) {
        Boolean b = scenicSpotService.addScenicSpotImage(addScenicSpotImageRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR, "添加失败");
        }
        return Result.success("添加成功");
    }

    @Override
    public Result deleteScenicSpotImage(DeleteScenicSpotImageRequest deleteScenicSpotImageRequest) {
        Boolean b = scenicSpotService.deleteScenicSpotImage(deleteScenicSpotImageRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR, "删除失败");
        }
        return Result.success("删除成功");
    }
}
