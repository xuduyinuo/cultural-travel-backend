package com.xudu.culturaltravelbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.constant.RouteConstant;
import com.xudu.culturaltravelbackend.constant.UserConstant;
import com.xudu.culturaltravelbackend.exception.ServiceException;
import com.xudu.culturaltravelbackend.mapper.ElementMapper;
import com.xudu.culturaltravelbackend.model.dto.routedto.*;
import com.xudu.culturaltravelbackend.model.entity.*;
import com.xudu.culturaltravelbackend.model.vo.ElementVO;
import com.xudu.culturaltravelbackend.model.vo.RouteVO;
import com.xudu.culturaltravelbackend.model.vo.ScenicAreaVO;
import com.xudu.culturaltravelbackend.model.vo.UserVO;
import com.xudu.culturaltravelbackend.service.*;
import com.xudu.culturaltravelbackend.mapper.RouteMapper;
import com.xudu.culturaltravelbackend.utils.DeleteUtil;
import com.xudu.culturaltravelbackend.utils.GetRequestUtil;
import com.xudu.culturaltravelbackend.utils.QiniuUtil;
import com.xudu.culturaltravelbackend.utils.TagMatchAlgorithmUtil;
import javafx.util.Pair;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private ElementMapper elementMapper;
    @Autowired
    private Gson gson;
    @Autowired
    private QiniuUtil qiniuUtil;


    @Override
    public Long addRoute(AddRouteRequest addRouteRequest) {

        String routeName = addRouteRequest.getRouteName();
        String routeInfo = addRouteRequest.getRouteInfo();
        MultipartFile[] routeImages = addRouteRequest.getRouteImages();
        Integer routeMileage = addRouteRequest.getRouteMileage();
        Integer spendTime = addRouteRequest.getSpendTime();
        String suitableTime = addRouteRequest.getSuitableTime();
        List<Long> alongScenicArea = addRouteRequest.getAlongScenicArea();
        List<Long> alongElementList = addRouteRequest.getAlongElement();



        UserVO loginUser = userService.getLoginUser();
        Long userId = loginUser.getId();

        List<String> routeTags = addRouteRequest.getRouteTags();
        if (StringUtils.isAnyBlank(routeName, routeInfo,  suitableTime)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR);
        }
        if (routeMileage == null || spendTime == null) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR);
        }

        if (CollectionUtils.isEmpty(alongScenicArea) || CollectionUtils.isEmpty(alongElementList) || CollectionUtils.isEmpty(routeTags)){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "图片或景区或标签元素或路线标签参数为空");
        }

        List<String> routeImagesList = new ArrayList<>();
        for (MultipartFile routeImage : routeImages){
            if (routeImage.isEmpty()){
                throw new ServiceException(ErrorCode.PARAMS_ERROR, "图片不能为空");
            }
            if (routeImage.getSize() > 1024 * 1024 * 5){
                throw new ServiceException(ErrorCode.PARAMS_ERROR, "图片大小不能超过5M");
            }
            String routeImageUrl = qiniuUtil.upload(routeImage);
            routeImagesList.add(routeImageUrl);
        }

        Route route = new Route();
        route.setRouteName(routeName);
        route.setRouteInfo(routeInfo);
        Gson gson = new Gson();
        // String[] routeImagesList = routeImages.split(",");
        // String routeImagesListJson = gson.toJson(routeImagesList);
        // System.out.println("===============================" + routeImagesListJson);
        String routeImagesListJson = gson.toJson(routeImagesList);

        String alongScenicAreaJson = gson.toJson(alongScenicArea);
        String routeTagsJson = gson.toJson(routeTags);
        route.setRouteTags(routeTagsJson);

        route.setAlongScenicAreas(alongScenicAreaJson);
        route.setRouteImage(routeImagesListJson);
        route.setRouteMileage(routeMileage);
        route.setSpendTime(spendTime);
        route.setSuitableTime(suitableTime);
        route.setUserId(userId);

        // 管理员添加路线直接审核成功
        if (loginUser.getUserRole() == UserConstant.ADMIN_ROLE){
            route.setRouteStatus(RouteConstant.ROUTE_STATUS_AUDIT_SUCCESS);
        }

        // 需要先存储route生成routeid，方便关联表存储
        this.save(route);

        // 处理沿途景区和沿途element
        // 添加沿途景区的时候需要判断是否已经存在，如果不存在返回错误
        // Gson gson = new Gson();

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
        // 检验id是否合法
        DeleteUtil.checkId(ids);

        // 删除和路线关联的element
        QueryWrapper<RouteElement> queryWrapperRouteElement = new QueryWrapper<>();

        queryWrapperRouteElement.lambda().in(RouteElement::getRouteId, ids);
        routeElementService.remove(queryWrapperRouteElement);

        return this.baseMapper.deleteBatchIds(ids);
    }

    @Override
    public Page<RouteVO> searchRouteListByPage(SearchRouteRequest searchRouteRequest) {
        // 管理员模式下，可以查看所有路线，普通用户模式下，只能查看自己创建的路线


        // 条件组合查询
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
        String tags = searchRouteRequest.getRouteTags();
        if (StringUtils.isNotBlank(tags)) {
            queryWrapper.lambda().like(Route::getRouteTags, tags);
        }


        // 获取当前用户
        UserVO loginUser = userService.getLoginUser();
        if (loginUser == null) {
            throw new ServiceException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Integer userRole = loginUser.getUserRole();


        int pageNum = searchRouteRequest.getPageNum();
        int pageSize = searchRouteRequest.getPageSize();
        Page<Route> page = new Page<>(pageNum, pageSize);
        if (userRole == UserConstant.DEFAULT_ROLE) {
            queryWrapper.lambda().eq(Route::getUserId, loginUser.getId());

            Page<Route> routePage = this.page(page, queryWrapper);
            List<Route> routeList = routePage.getRecords();

            // 转换
            List<RouteVO> routeVOList = this.getRouteListToRouteVOList(routeList);

            Page<RouteVO> routeVOPage = new Page<>(routePage.getCurrent(), routePage.getSize(), routePage.getTotal());
            routeVOPage.setRecords(routeVOList);
            return routeVOPage;
        }
        if (userRole == UserConstant.ADMIN_ROLE) {
            //管理员还可以按照用户和线路状态查询
            Long userId = searchRouteRequest.getUserId();
            if (userId != null && userId > 0) {
                queryWrapper.lambda().eq(Route::getUserId, userId);
            }
            Integer routeStatus = searchRouteRequest.getRouteStatus();
            if (routeStatus != null && routeStatus > 0) {
                queryWrapper.lambda().eq(Route::getRouteStatus, routeStatus);
            }
            //Page<Route> page = new Page<>(pageNum, pageSize);
            Page<Route> routePage = this.page(page, queryWrapper);
            List<Route> routeList = routePage.getRecords();

            // 转换
            List<RouteVO> routeVOList = this.getRouteListToRouteVOList(routeList);
            Page<RouteVO> routeVOPage = new Page<>(routePage.getCurrent(), routePage.getSize(), routePage.getTotal());
            routeVOPage.setRecords(routeVOList);

            return routeVOPage;
        }

        throw new ServiceException(ErrorCode.NOT_LOGIN_ERROR);
    }

    @Override
    public List<RouteVO> getRouteListToRouteVOList(List<Route> routeList) {
        List<RouteVO> routeVOList = new ArrayList<>();
        for (Route route : routeList) {
            RouteVO routeVO = new RouteVO();
            BeanUtils.copyProperties(route, routeVO);

            // 处理创建人
            Long userId = route.getUserId();
            User user = userService.getById(userId);
            routeVO.setCreateRouteUserAccount(user.getUserAccount());

            // 处理routeTags
            Gson gson = new Gson();
            String routeImage = route.getRouteImage();
            List<String> routeImageList = gson.fromJson(routeImage, new TypeToken<List<String>>(){}.getType());
            routeVO.setRouteImage(routeImageList);

            String routeTags = route.getRouteTags();
            List<String> routeTagsList = gson.fromJson(routeTags, new TypeToken<List<String>>() {
            }.getType());

            routeVO.setRouteTags(routeTagsList);


            String alongScenicAreas = route.getAlongScenicAreas();
            List<Long> alongScenicAreasIds = gson.fromJson(alongScenicAreas, new TypeToken<List<Long>>() {
            }.getType());

            QueryWrapper<ScenicArea> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().in(ScenicArea::getId, alongScenicAreasIds);
            List<ScenicArea> scenicAreasList = scenicAreaService.list(queryWrapper);
            if (CollectionUtils.isNotEmpty(scenicAreasList)) {
                routeVO.setAlongScenicAreaVO(scenicAreasList.stream().map(scenicArea -> {
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
                List<Element> elementList = elementMapper.selectList(elementQueryWrapper);
                routeVO.setAlongElementVO(elementList.stream().map(element -> {
                    ElementVO elementVO = new ElementVO();
                    BeanUtils.copyProperties(element, elementVO);
                    return elementVO;
                }).collect(Collectors.toList()));
            }
            routeVOList.add(routeVO);
        }
        return routeVOList;
    }

    @Override
    public Boolean auditRoute(Long id) {
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR);
        }
        Route dbroute = this.getById(id);
        if (dbroute == null) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR);
        }
        if (dbroute.getRouteStatus() == RouteConstant.ROUTE_STATUS_AUDIT_SUCCESS) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "该路线已审核通过");
        }

        Route route = new Route();
        route.setId(id);
        route.setRouteStatus(RouteConstant.ROUTE_STATUS_AUDIT_SUCCESS);
        return this.updateById(route);
    }

    @Override
    public Boolean updateRoute(UpdateRouteRequest updateRouteRequest) {
        Long id = updateRouteRequest.getId();
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        Route dbroute = this.getById(id);
        if (ObjectUtils.isEmpty(dbroute)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "更改的线路不存在");
        }

        Route route = new Route();
        BeanUtils.copyProperties(updateRouteRequest, route);
        List<String> routeTags = updateRouteRequest.getRouteTags();
        if (CollectionUtils.isNotEmpty(routeTags)){
            String jsonRouteTags = gson.toJson(routeTags);
            route.setRouteTags(jsonRouteTags);
        }

        Boolean isAdmin = userService.isAdmin();

        // 管理员可以修改一切
        if (!isAdmin) {
            // 普通用户只能修改自己的且未审核的线路
            Long loginUserId = userService.getLoginUser().getId();
            Long dbrouteUserId = dbroute.getUserId();
            if (!(dbrouteUserId == null || !dbrouteUserId.equals(loginUserId))) {
                throw new ServiceException(ErrorCode.NO_AUTH_ERROR);
            }
            Integer dbRouteStatus = dbroute.getRouteStatus();
            if (dbRouteStatus == null || dbRouteStatus.equals(RouteConstant.ROUTE_STATUS_AUDIT_SUCCESS)){
                throw new ServiceException(ErrorCode.PARAMS_ERROR, "该路线已审核通过，无法修改");
            };
            // return this.updateById(route);
        }

        return this.updateById(route);
    }

    @Override
    public Boolean updateRouteImage(UpdateRouteImageRequest updateRouteImageRequest) {
        Long id = updateRouteImageRequest.getId();
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        Route dbroute = this.getById(id);
        if (ObjectUtils.isEmpty(dbroute)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "更改图片的线路不存在");
        }

        MultipartFile routeImage = updateRouteImageRequest.getRouteImage();
        if (ObjectUtils.isEmpty(routeImage)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "图片为空");
        }
        String routeImageUrl = qiniuUtil.upload(routeImage);
        String dbRouteImage = dbroute.getRouteImage();
        Gson gson = new Gson();
        List<String> imageList = gson.fromJson(dbRouteImage, new TypeToken<List<String>>(){}.getType());
        Long imageIndex = updateRouteImageRequest.getIndex();
        if (imageIndex == null || imageIndex < 0 || imageIndex >= imageList.size()) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "图片索引错误");
        }
        imageList.set(imageIndex.intValue(), routeImageUrl);
        String jsonRouteImage = gson.toJson(imageList);

        Route route = new Route();
        route.setId(id);
        route.setRouteImage(jsonRouteImage);
        return this.updateById(route);
    }

    @Override
    public Boolean addRouteImage(AddRouteImageRequest addRouteImageRequest) {
        Long id = addRouteImageRequest.getId();
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        Route dbroute = this.getById(id);
        if (ObjectUtils.isEmpty(dbroute)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "添加图片的线路不存在");
        }
        MultipartFile routeImage = addRouteImageRequest.getRouteImage();
        if (ObjectUtils.isEmpty(routeImage)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "图片为空");
        }
        String routeImageUrl = qiniuUtil.upload(routeImage);
        String dbRouteImage = dbroute.getRouteImage();
        Gson gson = new Gson();
        List<String> imageList = gson.fromJson(dbRouteImage, new TypeToken<List<String>>(){}.getType());
        imageList.add(routeImageUrl);
        String jsonRouteImage = gson.toJson(imageList);
        Route route = new Route();
        route.setId(id);
        route.setRouteImage(jsonRouteImage);
        return this.updateById(route);
    }

    @Override
    public Boolean deleteRouteImage(DeleteRouteImageRequest deleteRouteImageRequest) {
        Long id = deleteRouteImageRequest.getId();
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        Route dbroute = this.getById(id);
        if (ObjectUtils.isEmpty(dbroute)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "删除图片的线路不存在");
        }

        String dbRouteImage = dbroute.getRouteImage();
        Gson gson = new Gson();
        List<String> imageList = gson.fromJson(dbRouteImage, new TypeToken<List<String>>(){}.getType());
        Long imageIndex = deleteRouteImageRequest.getIndex();
        if ((imageIndex == null) || (imageIndex < 0)|| (imageIndex >= imageList.size())) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "图片索引错误");
        }
        imageList.remove(imageIndex.intValue());
        String jsonRouteImage = gson.toJson(imageList);
        Route route = new Route();
        route.setId(id);
        route.setRouteImage(jsonRouteImage);
        return this.updateById(route);
    }

    @Override
    public List<RouteVO> recommendRouteByUserTags(long num, UserVO loginUser) {
        QueryWrapper<Route> queryWrapper = new QueryWrapper<>();
        //queryWrapper.select("id", "routeTags");
        queryWrapper.isNotNull("routeTags");
        List<Route> routeList = this.list(queryWrapper);

        List<String> userTagsList = loginUser.getUserTags();
        // String userTags = loginUser.getUserTags();
        Gson gson = new Gson();
        // List<String> userTagsList = gson.fromJson(userTags, new TypeToken<List<String>>() {
        // }.getType());

        //依次计算用户标签和路线标签之间的相似度
        List<Pair<Route, Long>> list = new ArrayList<>();
        for (Route route : routeList) {
            String routeTags = route.getRouteTags();
            if (StringUtils.isBlank(routeTags)) {
                continue;
            }
            List<String> routeTagsList = gson.fromJson(routeTags, new TypeToken<List<String>>() {
            }.getType());
            long similarity = TagMatchAlgorithmUtil.minDistance(userTagsList, routeTagsList);
            list.add(new Pair<>(route, similarity));

        }

        //按相似度排序
        List<Pair<Route, Long>> topRoutePairList = list.stream()
                .sorted((a, b) -> (int) (a.getValue() - b.getValue()))
                .limit(num)
                .collect(Collectors.toList());


        //原本的顺序列表
        //List<Route> topRouteList = topRoutePairList.stream().map(Pair::getKey).collect(Collectors.toList());
        ArrayList<Route> finalRouteList = new ArrayList<>();
        for (Pair<Route, Long> pair : topRoutePairList) {
            finalRouteList.add(pair.getKey());
        }
        return this.getRouteListToRouteVOList(finalRouteList);
    }

    @Override
    public Boolean updateAlongScenicArea(UpdateRouteAlongScenicAreaRequest updateRouteAlongScenicAreaRequest) {
        Long id = updateRouteAlongScenicAreaRequest.getId();
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        List<Long> routeAlongScenicArea = updateRouteAlongScenicAreaRequest.getRouteAlongScenicArea();
        //判断更新的景区列表是否存在
        routeAlongScenicArea.forEach(scenicAreaId -> {
            ScenicArea scenicArea = scenicAreaService.getById(scenicAreaId);
            if (ObjectUtils.isEmpty(scenicArea)) {
                throw new ServiceException(ErrorCode.PARAMS_ERROR, "景区id有误");
            }
        });
        Route dbRoute = this.getById(id);
        if (ObjectUtils.isEmpty(dbRoute)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "路线不存在");
        }
        Route route = new Route();
        Gson gson = new Gson();
        String routeAlongScenicAreaJson = gson.toJson(routeAlongScenicArea);
        route.setId(id);
        route.setAlongScenicAreas(routeAlongScenicAreaJson);
        return this.updateById(route);


    }

    @Override
    public Boolean deleteAlongScenicArea(DeleteRouteAlongScenicAreaRequest deleteRouteAlongScenicAreaRequest) {
        return null;
    }


}








