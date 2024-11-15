package com.xudu.culturaltravelbackend.controller.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.controller.RouteController;
import com.xudu.culturaltravelbackend.model.dto.routedto.*;
import com.xudu.culturaltravelbackend.model.vo.RouteVO;
import com.xudu.culturaltravelbackend.model.vo.UserVO;
import com.xudu.culturaltravelbackend.service.RouteService;
import com.xudu.culturaltravelbackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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


    @Resource
    private UserService userService;

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
        Boolean b = routeService.updateRoute(updateRouteRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR);
        }
        return Result.success("更新成功");

    }

    @Override
    public Result auditRoute(Long id) {
        Boolean b = routeService.auditRoute(id);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR);
        }
        return Result.success("审核成功");
    }

    @Override
    public Result addRouteImage(AddRouteImageRequest addRouteImageRequest) {
        Boolean b = routeService.addRouteImage(addRouteImageRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR);
        }
        return Result.success("添加成功");
    }

    @Override
    public Result deleteRouteImage(DeleteRouteImageRequest deleteRouteImageRequest) {
        Boolean b = routeService.deleteRouteImage(deleteRouteImageRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR);
        }
        return Result.success("删除成功");
    }

    @Override
    public Result updateRouteImage(UpdateRouteImageRequest updateRouteImageRequest) {
        Boolean b = routeService.updateRouteImage(updateRouteImageRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR);
        }
        return Result.success("更新成功");
    }

    @Override
    public Result recommendRouteByUserTags() {
        long num = 5;
        UserVO loginUser = userService.getLoginUser();
        List<RouteVO> routeVOS = routeService.recommendRouteByUserTags(num, loginUser);
        return Result.success(routeVOS);

    }

    @Override
    public Result addAlongElement(UpdateRouteAlongScenicAreaRequest updateRouteAlongScenicAreaRequest) {
        Boolean b = routeService.updateAlongScenicArea(updateRouteAlongScenicAreaRequest);
        if (!b){
            return Result.error(ErrorCode.SYSTEM_ERROR, "更新失败");
        }
        return Result.success("更新成功");
    }
}
