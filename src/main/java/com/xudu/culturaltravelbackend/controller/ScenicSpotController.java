package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.*;
import com.xudu.culturaltravelbackend.model.dto.scenicSpotdto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @ClassName ScenicSpotController
 * @Description
 * @Author xudu
 * @Time 2024/10/27 15:16
 */
@Api(tags = "景点模块")
public interface ScenicSpotController {


    /**
     * 添加景点
     * @param addScenicSpotRequest 添加景点请求
     * @return 新增景点id
     */
    @ApiOperation("添加景点")
    @PostMapping("/add")
    Result addScenicSpot(AddScenicSpotRequest addScenicSpotRequest);


    /**
     * 搜索景点
     * @param searchScenicSpotRequest 搜索景点请求
     * @return 搜索分页结果列表
     */
    @ApiOperation("搜索景点分页列表")
    @GetMapping("/search/list/page")
    Result searchScenicSpotListByPage(SearchScenicSpotRequest searchScenicSpotRequest);

    /**
     * 删除景点
     * @param deleteBatchRequest 景点id列表
     * @return 删除结果
     */
    @ApiOperation("删除景点")
    @PostMapping("/delete")
    Result deleteScenicSpot(DeleteBatchRequest deleteBatchRequest);

    /**
     * 修改景点
     * @param updateScenicSpotRequest 修改景点请求
     * @return 修改结果
     */
    @ApiOperation("更新景点")
    @PostMapping("/update")
    Result updateScenicSpot(UpdateScenicSpotRequest updateScenicSpotRequest);

    /**
     * 修改景点图片
     * @param updateScenicSpotImageRequest
     * @return
     */
    @ApiOperation("更新景点图片")
    @PostMapping("/update/image")
    Result updateScenicSpotImage(UpdateScenicSpotImageRequest updateScenicSpotImageRequest);

    /**
     * 添加景点图片
     * @param addScenicSpotImageRequest
     * @return
     */
    @ApiOperation("添加景点图片")
    @PostMapping("/update/addimage")
    Result addScenicSpotImage(AddScenicSpotImageRequest addScenicSpotImageRequest);

    /**
     * 删除景点图片
     * @param deleteScenicSpotImageRequest
     * @return
     */
    @ApiOperation("删除景点图片")
    @PostMapping("/update/deleteimage")
    Result deleteScenicSpotImage(DeleteScenicSpotImageRequest deleteScenicSpotImageRequest);
}
