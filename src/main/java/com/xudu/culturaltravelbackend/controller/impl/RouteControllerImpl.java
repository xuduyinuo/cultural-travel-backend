package com.xudu.culturaltravelbackend.controller.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.controller.RouteController;
import com.xudu.culturaltravelbackend.model.dto.routedto.*;
import com.xudu.culturaltravelbackend.model.vo.RouteVO;
import com.xudu.culturaltravelbackend.service.RouteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    private RouteService routeService;

    @Override
    public Result addRoute(AddRouteRequest addRouteRequest) {
        Long routeId = routeService.addRoute(addRouteRequest);

        return Result.success(routeId);
    }

    @Override
    public Result deleteRoute(DeleteBatchRequest deleteBatchRequest) {
        Integer i = routeService.deleteRoute(deleteBatchRequest.getIds());
        return Result.success(i);
    }

    @Override
    public Result searchRouteListByPage(SearchRouteRequest searchRouteRequest) {
        Page<RouteVO> routeVOPage = routeService.searchRouteListByPage(searchRouteRequest);
        return Result.success(routeVOPage);
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
