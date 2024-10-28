package com.xudu.culturaltravelbackend.controller.impl;

import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.controller.RouteController;
import com.xudu.culturaltravelbackend.model.dto.routedto.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RouteControllerImpl
 * @Description
 * @Author xudu
 * @Time 2024/10/28 18:48
 */
@CrossOrigin
@RequestMapping("/route")
@RestController
public class RouteControllerImpl implements RouteController {

    @Override
    public Result addRoute(AddRouteRequest addRouteRequest) {
        return null;
    }

    @Override
    public Result deleteRoute(DeleteBatchRequest deleteBatchRequest) {
        return null;
    }

    @Override
    public Result searchRouteListByPage(SearchRouteRequest searchRouteRequest) {
        return null;
    }

    @Override
    public Result updateRoute(UpdateRouteRequest updateRouteRequest) {
        return null;
    }

    @Override
    public Result auditRoute(Long id) {
        return null;
    }

    @Override
    public Result addRouteImage(AddRouteImageRequest addRouteImageRequest) {
        return null;
    }

    @Override
    public Result deleteRouteImage(DeleteRouteImageRequest deleteRouteImageRequest) {
        return null;
    }

    @Override
    public Result updateRouteImage(UpdateRouteImageRequest updateRouteImageRequest) {
        return null;
    }
}
