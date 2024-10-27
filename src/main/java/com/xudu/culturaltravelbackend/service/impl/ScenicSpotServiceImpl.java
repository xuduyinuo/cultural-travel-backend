package com.xudu.culturaltravelbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.exception.ServiceException;
import com.xudu.culturaltravelbackend.model.dto.scenicSpotdto.*;
import com.xudu.culturaltravelbackend.model.entity.ScenicArea;
import com.xudu.culturaltravelbackend.model.entity.ScenicSpot;
import com.xudu.culturaltravelbackend.model.vo.ScenicSpotVO;
import com.xudu.culturaltravelbackend.service.ScenicAreaService;
import com.xudu.culturaltravelbackend.service.ScenicSpotService;
import com.xudu.culturaltravelbackend.mapper.ScenicSpotMapper;
import com.xudu.culturaltravelbackend.utils.DeleteUtil;
import com.xudu.culturaltravelbackend.utils.QiniuUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

/**
* @author xudu
* @description 针对表【scenic_spot】的数据库操作Service实现
* @createDate 2024-10-27 15:01:04
*/
@Service
public class ScenicSpotServiceImpl extends ServiceImpl<ScenicSpotMapper, ScenicSpot> implements ScenicSpotService{

    @Resource
    private QiniuUtil qiniuUtil;

    @Resource
    private ScenicAreaService scenicAreaService;

    @Override
    public Long saveScenicSpot(AddScenicSpotRequest addScenicSpotRequest) {

        String scenicSpotName = addScenicSpotRequest.getScenicSpotName();
        if (StringUtils.isBlank(scenicSpotName)){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景点名称不能为空");
        }
        String scenicSpotInfo = addScenicSpotRequest.getScenicSpotInfo();
        if (StringUtils.isBlank(scenicSpotInfo)){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景点信息不能为空");
        }
        MultipartFile[] scenicSpotImages = addScenicSpotRequest.getScenicSpotImages();
        if (scenicSpotImages == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景点图片不能为空");
        }
        Long scenicAreaId = addScenicSpotRequest.getScenicAreaId();
        if (scenicAreaId == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景点对应的景区id不能为空");
        }
        QueryWrapper<ScenicArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ScenicArea::getId, scenicAreaId);
        ScenicArea scenicArea = scenicAreaService.getOne(queryWrapper);
        if (scenicArea == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景点对应的景区不存在");
        }
        List<String> scenicSpotImageList = new ArrayList<>();
        for (MultipartFile file : scenicSpotImages){
            String fileName = qiniuUtil.upload(file);
            scenicSpotImageList.add(fileName);
        }
        Gson gson = new Gson();
        String jsonScenicSpotImage = gson.toJson(scenicSpotImageList);
        ScenicSpot scenicSpot = new ScenicSpot();
        scenicSpot.setScenicSpotName(scenicSpotName);
        scenicSpot.setScenicSpotInfo(scenicSpotInfo);
        scenicSpot.setScenicSpotImage(jsonScenicSpotImage);
        scenicSpot.setScenicAreaId(scenicAreaId);
        this.save(scenicSpot);
        return scenicSpot.getId();


    }

    @Override
    public Integer deleteScenicSpot(List<Long> ids) {
        DeleteUtil.checkId(ids);
        return this.baseMapper.deleteBatchIds(ids);
    }

    @Override
    public Boolean updateScenicSpot(UpdateScenicSpotRequest updateScenicSpotRequest) {
        Long id = updateScenicSpotRequest.getId();
        if (id == null || id <= 0){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ScenicSpot::getId, id);
        ScenicSpot dbScenicSpot = this.getOne(queryWrapper);
        if (dbScenicSpot == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景点不存在");
        }
        ScenicSpot scenicSpot = new ScenicSpot();
        BeanUtils.copyProperties(updateScenicSpotRequest, scenicSpot);
        return this.updateById(scenicSpot);

    }

    @Override
    public Page<ScenicSpotVO> searchScenicSpotListByPage(SearchScenicSpotRequest searchScenicSpotRequest) {
        QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<>();

        // 条件组合查询
        Long id = searchScenicSpotRequest.getId();
        if (id != null && id > 0){
            queryWrapper.lambda().eq(ScenicSpot::getId, id);
        }
        String scenicSpotName = searchScenicSpotRequest.getScenicSpotName();
        if (scenicSpotName != null){
            queryWrapper.lambda().like(ScenicSpot::getScenicSpotName, scenicSpotName);
        }
        Long scenicAreaId = searchScenicSpotRequest.getScenicAreaId();
        if (scenicAreaId != null){
            queryWrapper.lambda().eq(ScenicSpot::getScenicAreaId, scenicAreaId);
        }

        int pageNum = searchScenicSpotRequest.getPageNum();
        int pageSize = searchScenicSpotRequest.getPageSize();

        Page<ScenicSpot> page = new Page<>(pageNum, pageSize);
        Page<ScenicSpot> scenicSpotPage = this.page(page, queryWrapper);

        Page<ScenicSpotVO> scenicSpotVOPage = new Page<>(scenicSpotPage.getCurrent(), scenicSpotPage.getSize(), scenicSpotPage.getTotal());
        List<ScenicSpotVO> scenicSpotVOList = new ArrayList<>();
        scenicSpotPage.getRecords().forEach(scenicSpot -> {
            ScenicSpotVO scenicSpotVO = new ScenicSpotVO();
            Gson gson = new Gson();
            scenicSpotVO.setScenicSpotImages(gson.fromJson(scenicSpot.getScenicSpotImage(), List.class));
            BeanUtils.copyProperties(scenicSpot, scenicSpotVO);
            scenicSpotVOList.add(scenicSpotVO);
        });
        scenicSpotVOPage.setRecords(scenicSpotVOList);
        return scenicSpotVOPage;
    }

    @Override
    public Boolean updateScenicSpotImage(UpdateScenicSpotImageRequest updateScenicSpotImageRequest) {
        Long id = updateScenicSpotImageRequest.getId();
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ScenicSpot::getId, id);
        ScenicSpot dbScenicSpot = this.getOne(queryWrapper);
        if (dbScenicSpot == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景点不存在");
        }
        Long imageIndex = updateScenicSpotImageRequest.getIndex();
        if (imageIndex == null || imageIndex < 0){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        String scenicSpotImage = dbScenicSpot.getScenicSpotImage();
        Gson gson = new Gson();
        List<String> imageList = gson.fromJson(scenicSpotImage, List.class);
        qiniuUtil.deleteFile(imageList.get(imageIndex.intValue()));
        imageList.set(imageIndex.intValue(), qiniuUtil.upload(updateScenicSpotImageRequest.getScenicSpotImage()));
        String jsonScenicSpotImage = gson.toJson(imageList);
        return this.update().set("scenicSpotImage", jsonScenicSpotImage).eq("id", id).update();
    }

    @Override
    public Boolean deleteScenicSpotImage(DeleteScenicSpotImageRequest deleteScenicSpotImageRequest) {
        Long id = deleteScenicSpotImageRequest.getId();
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ScenicSpot::getId, id);
        ScenicSpot dbScenicSpot = this.getOne(queryWrapper);
        if (dbScenicSpot == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景区不存在");
        }
        Long imageIndex = deleteScenicSpotImageRequest.getIndex();
        if (imageIndex == null || imageIndex < 0){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        String scenicSpotImage = dbScenicSpot.getScenicSpotImage();
        Gson gson = new Gson();
        List<String> imageList = gson.fromJson(scenicSpotImage, List.class);
        qiniuUtil.deleteFile(imageList.get(imageIndex.intValue()));
        imageList.remove(imageIndex.intValue());
        String jsonScenicSpotImage = gson.toJson(imageList);
        return this.update().set("scenicSpotImage", jsonScenicSpotImage).eq("id", id).update();
    }

    @Override
    public Boolean addScenicSpotImage(AddScenicSpotImageRequest addScenicSpotImageRequest) {
        Long id = addScenicSpotImageRequest.getId();
        if (id == null || id <= 0){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        MultipartFile scenicSpotImage = addScenicSpotImageRequest.getScenicSpotImage();
        if (scenicSpotImage == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "上传图片为空");
        }
        QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ScenicSpot::getId, id);
        ScenicSpot dbScenicSpot = this.getOne(queryWrapper);
        String dbScenicSpotImage = dbScenicSpot.getScenicSpotImage();
        Gson gson = new Gson();
        List<String> imageList = gson.fromJson(dbScenicSpotImage, List.class);
        imageList.add(qiniuUtil.upload(scenicSpotImage));
        String jsonScenicSpotImage = gson.toJson(imageList);
        return this.update().set("scenicSpotImage", jsonScenicSpotImage).eq("id", id).update();
    }
}




