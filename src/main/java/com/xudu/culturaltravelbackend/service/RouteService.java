package com.xudu.culturaltravelbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xudu.culturaltravelbackend.model.dto.routedto.AddRouteRequest;
import com.xudu.culturaltravelbackend.model.dto.routedto.SearchRouteRequest;
import com.xudu.culturaltravelbackend.model.dto.routedto.UpdateRouteRequest;
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


    /**
     * 新增旅游线路
     * @param addRouteRequest
     * @return
     */
    Long addRoute(AddRouteRequest addRouteRequest);

    /**
     * 删除旅游线路 (可批量)
     * @param ids
     * @return
     */
    Integer deleteRoute(List<Long> ids);

    /**
     * 分页查询旅游线路
     * @param searchRouteRequest
     * @return
     */
    Page<RouteVO> searchRouteListByPage(SearchRouteRequest searchRouteRequest);

    /**
     * 获取routeVO 将routeList 转为 routeVOList
     * @param routeList
     * @return
     */
    List<RouteVO> getRouteListToRouteVOList(List<Route> routeList);

    /**
     * 审核旅游线路
     * @param id
     * @return
     */
    Boolean auditRoute(Long id);

    /**
     * 修改旅游线路
     * @param updateRouteRequest
     * @return
     */
    Boolean updateRoute(UpdateRouteRequest updateRouteRequest);


}
