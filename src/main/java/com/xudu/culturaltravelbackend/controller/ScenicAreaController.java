package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @className: ScenicAreaController
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-26
 */
public interface ScenicAreaController {

    /**
     * 添加景点
     * @param addScenicAreaRequest 添加景区请求
     * @return 新增景区id
     */
    @PostMapping("/add")
    Result addScenicArea(AddScenicAreaRequest addScenicAreaRequest);


    /**
     * 搜索景区
     * @param searchScenicAreaRequest 搜索景区请求
     * @return 搜索分页结果列表
     */
    @GetMapping("/search/list/page")
    Result searchScenicAresListByPage(SearchScenicAreaRequest searchScenicAreaRequest);

    /**
     * 删除景区
     * @param ids 景区id列表
     * @return 删除结果
     */
    @PostMapping("/delete")
    Result deleteScenicArea(List<Long> ids);

    /**
     * 修改景区
     * @param updateScenicAreaRequest 修改景区请求
     * @return 修改结果
     */
    @PostMapping("/update")
    Result updateScenicArea(UpdateScenicAreaRequest updateScenicAreaRequest);

    /**
     * 修改景区图片
     * @param updateScenicAreaImageRequest
     * @return
     */
    @PostMapping("/update/image")
    Result updateScenicAreaImage(UpdateScenicAreaImageRequest updateScenicAreaImageRequest);

    /**
     * 添加景区图片
     * @param addScenicAreaImageRequest
     * @return
     */
    @PostMapping("/update/addimage")
    Result addScenicAreaImage(AddScenicAreaImageRequest addScenicAreaImageRequest);

    /**
     * 删除景区图片
     * @param deleteScenicAreaImageRequest
     * @return
     */
    @PostMapping("/update/deleteimage")
    Result deleteScenicAreaImage(DeleteScenicAreaImageRequest deleteScenicAreaImageRequest);

}