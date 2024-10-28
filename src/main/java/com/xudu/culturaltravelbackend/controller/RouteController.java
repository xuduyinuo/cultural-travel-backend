package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.model.dto.routedto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName RouteController
 * @Description
 * @Author xudu
 * @Time 2024/10/28 18:47
 */
public interface RouteController {

    /**
     * 添加路线
     */
    @PostMapping("/add")
    Result addRoute(AddRouteRequest addRouteRequest);

    /**
     * 删除路线
     */
    @PostMapping("/delete")
    Result deleteRoute(DeleteBatchRequest deleteBatchRequest);


    /**
     * 搜索路线
     */
    @GetMapping("/search/list/page")
    Result searchRouteListByPage(SearchRouteRequest searchRouteRequest);

    /**
     * 更新路线
     */
    @PostMapping("/update")
    Result updateRoute(UpdateRouteRequest updateRouteRequest);


    /**
     * 审核路线
     */
    @PostMapping("/audit")
    Result auditRoute(Long id);

    /**
     * 添加路线图片
     * @param addRouteImageRequest
     * @return
     */
    @PostMapping("/add/image")
    Result addRouteImage(AddRouteImageRequest addRouteImageRequest);

    /**
     * 删除路线图片
     * @param deleteRouteImageRequest
     * @return
     */
    @PostMapping("/delete/image")
    Result deleteRouteImage(DeleteRouteImageRequest deleteRouteImageRequest);

    /**
     * 更新路线图片
     */
    @PostMapping("/update/image")
    Result updateRouteImage(UpdateRouteImageRequest updateRouteImageRequest);

}
