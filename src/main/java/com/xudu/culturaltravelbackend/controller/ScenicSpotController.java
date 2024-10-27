package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.*;
import com.xudu.culturaltravelbackend.model.dto.scenicSpotdto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @ClassName ScenicSpotController
 * @Description
 * @Author xudu
 * @Time 2024/10/27 15:16
 */
public interface ScenicSpotController {


    /**
     * 添加景点
     * @param addScenicSpotRequest 添加景点请求
     * @return 新增景点id
     */
    @PostMapping("/add")
    Result addScenicSpot(AddScenicSpotRequest addScenicSpotRequest);


    /**
     * 搜索景点
     * @param searchScenicSpotRequest 搜索景点请求
     * @return 搜索分页结果列表
     */
    @GetMapping("/search/list/page")
    Result searchScenicSpotListByPage(SearchScenicSpotRequest searchScenicSpotRequest);

    /**
     * 删除景点
     * @param deleteBatchRequest 景点id列表
     * @return 删除结果
     */
    @PostMapping("/delete")
    Result deleteScenicSpot(DeleteBatchRequest deleteBatchRequest);

    /**
     * 修改景点
     * @param updateScenicSpotRequest 修改景点请求
     * @return 修改结果
     */
    @PostMapping("/update")
    Result updateScenicSpot(UpdateScenicSpotRequest updateScenicSpotRequest);

    /**
     * 修改景点图片
     * @param updateScenicSpotImageRequest
     * @return
     */
    @PostMapping("/update/image")
    Result updateScenicSpotImage(UpdateScenicSpotImageRequest updateScenicSpotImageRequest);

    /**
     * 添加景点图片
     * @param addScenicSpotImageRequest
     * @return
     */
    @PostMapping("/update/addimage")
    Result addScenicSpotImage(AddScenicSpotImageRequest addScenicSpotImageRequest);

    /**
     * 删除景点图片
     * @param deleteScenicSpotImageRequest
     * @return
     */
    @PostMapping("/update/deleteimage")
    Result deleteScenicSpotImage(DeleteScenicSpotImageRequest deleteScenicSpotImageRequest);
}
