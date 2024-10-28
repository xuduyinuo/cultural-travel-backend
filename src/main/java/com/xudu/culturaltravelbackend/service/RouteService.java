package com.xudu.culturaltravelbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xudu.culturaltravelbackend.model.dto.routedto.AddRouteRequest;
import com.xudu.culturaltravelbackend.model.entity.Route;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xudu.culturaltravelbackend.model.vo.RouteVO;

import java.util.List;

/**
* @author xudu
* @description 针对表【route】的数据库操作Service
* @createDate 2024-10-28 11:41:53
*/
public interface RouteService extends IService<Route> {


    Long addRoute(AddRouteRequest addRouteRequest);


    Integer deleteRoute(List<Long> ids);


    Page<RouteVO> searchRouteListByPage();


}
