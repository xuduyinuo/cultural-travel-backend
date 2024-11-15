package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.model.dto.routedto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName RouteController
 * @Description
 * @Author xudu
 * @Time 2024/10/28 18:47
 */
@Api(tags = "路线模块")
public interface RouteController {

    /**
     * 添加路线
     */
    @ApiOperation("添加路线")
    @PostMapping("/add")
    Result addRoute(@RequestBody AddRouteRequest addRouteRequest);

    /**
     * 删除路线
     */
    @ApiOperation("删除路线")
    @PostMapping("/delete")
    Result deleteRoute(@RequestBody DeleteBatchRequest deleteBatchRequest);


    /**
     * 搜索路线
     */
    @ApiOperation("搜索路线分页列表")
    @GetMapping("/search/list/page")
    Result searchRouteListByPage(SearchRouteRequest searchRouteRequest);

    /**
     * 更新路线
     */
    @ApiOperation("更新路线")
    @PostMapping("/update")
    Result updateRoute(UpdateRouteRequest updateRouteRequest);


    /**
     * 审核路线
     */
    @ApiOperation("审核路线")
    @PostMapping("/audit")
    Result auditRoute(Long id);

    /**
     * 添加路线图片
     * @param addRouteImageRequest 添加路线图片参数
     * @return
     */
    @ApiOperation("添加路线图片")
    @PostMapping("/add/image")
    Result addRouteImage(AddRouteImageRequest addRouteImageRequest);

    /**
     * 删除路线图片
     * @param deleteRouteImageRequest
     * @return
     */
    @ApiOperation("删除路线图片")
    @PostMapping("/delete/image")
    Result deleteRouteImage(DeleteRouteImageRequest deleteRouteImageRequest);

    /**
     * 更新路线图片
     */
    @ApiOperation("更新路线图片")
    @PostMapping("/update/image")
    Result updateRouteImage(UpdateRouteImageRequest updateRouteImageRequest);

    /**
     * 根据标签推荐路线
     */
    @ApiOperation("根据用户标签推荐路线")
    @GetMapping("/recommend")
    Result recommendRouteByUserTags();


    @ApiOperation("更新路线沿途元素")
    @PostMapping("/update/alongScenicArea")
    Result addAlongElement(@RequestBody UpdateRouteAlongScenicAreaRequest updateRouteAlongScenicAreaRequest);


}
