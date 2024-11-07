package com.xudu.culturaltravelbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.exception.ServiceException;
import com.xudu.culturaltravelbackend.model.dto.routedto.AddRouteRequest;
import com.xudu.culturaltravelbackend.model.dto.routedto.SearchRouteRequest;
import com.xudu.culturaltravelbackend.model.entity.*;
import com.xudu.culturaltravelbackend.model.vo.ElementVO;
import com.xudu.culturaltravelbackend.model.vo.RouteVO;
import com.xudu.culturaltravelbackend.model.vo.ScenicAreaVO;
import com.xudu.culturaltravelbackend.model.vo.UserVO;
import com.xudu.culturaltravelbackend.service.*;
import com.xudu.culturaltravelbackend.mapper.RouteMapper;
import com.xudu.culturaltravelbackend.utils.DeleteUtil;
import com.xudu.culturaltravelbackend.utils.GetRequestUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xudu
 * @description 针对表【route】的数据库操作Service实现
 * @createDate 2024-10-28 11:41:53
 */
@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route>
        implements RouteService {

    @Resource
    private UserService userService;

    @Resource
    private RouteScenicareaService routeScenicareaService;

    @Resource
    private RouteElementService routeElementService;

    @Resource
    private ScenicAreaService scenicAreaService;

    @Resource
    private ElementService elementService;


    @Override
    public Long addRoute(@RequestBody AddRouteRequest addRouteRequest) {
        String routeName = addRouteRequest.getRouteName();
        String routeInfo = addRouteRequest.getRouteInfo();
        String routeImages = addRouteRequest.getRouteImages();
        Integer routeMileage = addRouteRequest.getRouteMileage();
        Integer spendTime = addRouteRequest.getSpendTime();
        String suitableTime = addRouteRequest.getSuitableTime();
        String alongScenicArea = addRouteRequest.getAlongScenicArea();
        String alongElement = addRouteRequest.getAlongElement();


        UserVO loginUser = userService.getLoginUser(GetRequestUtil.getRequest());
        Long userId = loginUser.getId();

        String routeTags = addRouteRequest.getRouteTags();
        if (StringUtils.isAnyBlank(routeName, routeInfo, routeImages, suitableTime, alongScenicArea, alongElement, routeTags)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR);
        }
        if (routeMileage == null || spendTime == null) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR);
        }
        Route route = new Route();
        route.setRouteName(routeName);
        route.setRouteInfo(routeInfo);
        route.setRouteImage(routeImages);
        route.setRouteMileage(routeMileage);
        route.setSpendTime(spendTime);
        route.setSuitableTime(suitableTime);
        route.setUserId(userId);

        //将标签转换为json字符串存储
        Gson gson = new Gson();
        String routeTagsJson = gson.toJson(routeTags);
        route.setRouteTags(routeTagsJson);

        //需要先存储route生成routeid，方便关联表存储
        this.save(route);

        //处理沿途景区和沿途element
        //添加沿途景区的时候需要判断是否已经存在，如果不存在返回错误
        //Gson gson = new Gson();
        List<Long> alongScenicAreaList = gson.fromJson(alongScenicArea, new TypeToken<List<Long>>() {
        }.getType());
        List<Long> alongElementList = gson.fromJson(alongElement, new TypeToken<List<Long>>() {
        }.getType());
        for (Long alongScenicAreaId : alongScenicAreaList) {
            RouteScenicarea routeScenicarea = new RouteScenicarea();
            routeScenicarea.setRouteId(route.getId());
            routeScenicarea.setScenicAreaId(alongScenicAreaId);
            routeScenicareaService.save(routeScenicarea);
        }
        for (Long alongElementId : alongElementList) {
            RouteElement routeElement = new RouteElement();
            routeElement.setElementId(alongElementId);
            routeElement.setRouteId(route.getId());
            routeElementService.save(routeElement);
        }

        return route.getId();

    }

    @Override
    public Integer deleteRoute(List<Long> ids) {
        //检验id是否合法
        DeleteUtil.checkId(ids);

        //删除和路线关联的景区和element
        QueryWrapper<RouteElement> queryWrapperRouteElement = new QueryWrapper<>();
        QueryWrapper<RouteScenicarea> queryWrapperRouteScenicarea = new QueryWrapper<>();

        queryWrapperRouteScenicarea.lambda().in(RouteScenicarea::getRouteId, ids);
        routeScenicareaService.remove(queryWrapperRouteScenicarea);
        queryWrapperRouteElement.lambda().in(RouteElement::getRouteId, ids);
        routeElementService.remove(queryWrapperRouteElement);

        return this.baseMapper.deleteBatchIds(ids);
    }

    @Override
    public Page<RouteVO> searchRouteListByPage(SearchRouteRequest searchRouteRequest) {
        //管理员模式下，可以查看所有路线，普通用户模式下，只能查看自己创建的路线


        //条件组合查询
        QueryWrapper<Route> queryWrapper = new QueryWrapper<>();
        Long id = searchRouteRequest.getId();
        if (id != null && id > 0) {
            queryWrapper.lambda().eq(Route::getId, id);
        }
        String routeName = searchRouteRequest.getRouteName();
        if (StringUtils.isNotBlank(routeName)) {
            queryWrapper.lambda().like(Route::getRouteName, routeName);
        }
        Integer routeMileage = searchRouteRequest.getRouteMileage();
        if (routeMileage != null && routeMileage > 0) {
            queryWrapper.lambda().eq(Route::getRouteMileage, routeMileage);
        }
        Integer spendTime = searchRouteRequest.getSpendTime();
        if (spendTime != null && spendTime > 0) {
            queryWrapper.lambda().eq(Route::getSpendTime, spendTime);
        }
        String suitableTime = searchRouteRequest.getSuitableTime();
        if (StringUtils.isNotBlank(suitableTime)) {
            queryWrapper.lambda().like(Route::getSuitableTime, suitableTime);
        }


        //获取当前用户
        UserVO loginUser = userService.getLoginUser(GetRequestUtil.getRequest());
        Integer userRole = loginUser.getUserRole();

        int pageNum = searchRouteRequest.getPageNum();
        int pageSize = searchRouteRequest.getPageSize();

        Page<Route> page = new Page<>(pageNum, pageSize);
        Page<Route> routePage = this.page(page, queryWrapper);
        List<Route> routeList = routePage.getRecords();
        List<RouteVO> routeVOList = new ArrayList<>();
        for (Route route : routeList) {
            RouteVO routeVO = new RouteVO();
            BeanUtils.copyProperties(route, routeVO);

            //处理routeTags
            //Gson gson = new Gson();
            //String routeTags = route.getRouteTags();
            //List<String> tagsList = gson.fromJson(routeTags, new TypeToken<List<String>>() {}.getType());
            //routeVO.setRouteTags(tagsList);

            QueryWrapper<RouteScenicarea> routeScenicAreaQueryWrapper = new QueryWrapper<>();
            routeScenicAreaQueryWrapper.lambda().eq(RouteScenicarea::getRouteId, route.getId());
            List<RouteScenicarea> routeScenicareaList = routeScenicareaService.list(routeScenicAreaQueryWrapper);
            if (CollectionUtils.isNotEmpty(routeScenicareaList)) {
                List<Long> scenicAreaIdList = routeScenicareaList.stream().map(RouteScenicarea::getScenicAreaId).collect(Collectors.toList());
                QueryWrapper<ScenicArea> scenicAreaQueryWrapper = new QueryWrapper<>();
                scenicAreaQueryWrapper.lambda().in(ScenicArea::getId, scenicAreaIdList);
                List<ScenicArea> scenicAreaList = scenicAreaService.list(scenicAreaQueryWrapper);
                routeVO.setAlongScenicAreaVO(scenicAreaList.stream().map(scenicArea -> {
                    ScenicAreaVO scenicAreaVO = new ScenicAreaVO();
                    BeanUtils.copyProperties(scenicArea, scenicAreaVO);
                    return scenicAreaVO;
                }).collect(Collectors.toList()));
            }


            QueryWrapper<RouteElement> routeElementQueryWrapper = new QueryWrapper<>();
            routeElementQueryWrapper.lambda().eq(RouteElement::getRouteId, route.getId());
            List<RouteElement> routeElementList = routeElementService.list(routeElementQueryWrapper);
            if (CollectionUtils.isNotEmpty(routeElementList)) {
                List<Long> elementIdList = routeElementList.stream().map(RouteElement::getElementId).collect(Collectors.toList());
                QueryWrapper<Element> elementQueryWrapper = new QueryWrapper<>();
                elementQueryWrapper.lambda().in(Element::getId, elementIdList);
                List<Element> elementList = elementService.list(elementQueryWrapper);
                routeVO.setAlongElementVO(elementList.stream().map(element -> {
                    ElementVO elementVO = new ElementVO();
                    BeanUtils.copyProperties(element, elementVO);
                    return elementVO;
                }).collect(Collectors.toList()));
            }
            routeVOList.add(routeVO);
        }
        Page<RouteVO> routeVOPage = new Page<>(routePage.getCurrent(), routePage.getSize(), routePage.getTotal());
        routeVOPage.setRecords(routeVOList);
        return routeVOPage;
    }

    //throw new ServiceException(ErrorCode.NOT_LOGIN_ERROR);
}








