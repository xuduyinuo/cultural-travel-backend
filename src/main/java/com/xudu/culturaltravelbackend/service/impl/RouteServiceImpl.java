package com.xudu.culturaltravelbackend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xudu.culturaltravelbackend.model.dto.routedto.AddRouteRequest;
import com.xudu.culturaltravelbackend.model.entity.Route;
import com.xudu.culturaltravelbackend.model.vo.RouteVO;
import com.xudu.culturaltravelbackend.service.RouteService;
import com.xudu.culturaltravelbackend.mapper.RouteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xudu
* @description 针对表【route】的数据库操作Service实现
* @createDate 2024-10-28 11:41:53
*/
@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route>
    implements RouteService{


    @Override
    public Long addRoute(AddRouteRequest addRouteRequest) {
        return 0L;
    }

    @Override
    public Integer deleteRoute(List<Long> ids) {
        return 0;
    }

    @Override
    public Page<RouteVO> searchRouteListByPage() {
        return null;
    }
}




