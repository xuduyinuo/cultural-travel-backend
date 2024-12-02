package com.xudu.culturaltravelbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.exception.ServiceException;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.*;
import com.xudu.culturaltravelbackend.model.entity.ScenicArea;
import com.xudu.culturaltravelbackend.model.entity.User;
import com.xudu.culturaltravelbackend.model.vo.ScenicAreaVO;
import com.xudu.culturaltravelbackend.service.ScenicAreaService;
import com.xudu.culturaltravelbackend.mapper.ScenicAreaMapper;
import com.xudu.culturaltravelbackend.utils.DeleteUtil;
import com.xudu.culturaltravelbackend.utils.QiniuUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author xudu
* @description 针对表【scenic_area】的数据库操作Service实现
* @createDate 2024-10-26 23:25:05
*/
@Service
public class ScenicAreaServiceImpl extends ServiceImpl<ScenicAreaMapper, ScenicArea> implements ScenicAreaService{

    @Resource
    private QiniuUtil qiniuUtil;
    @Autowired
    private DeleteUtil deleteUtil;


    @Override
    public Long saveScenicArea(AddScenicAreaRequest addScenicAreaRequest) {

        //参数校验 todo 优化
        String scenicAreaName = addScenicAreaRequest.getScenicAreaName();
        if (scenicAreaName == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景区名称不能为空");
        }
        String scenicAreaInfo = addScenicAreaRequest.getScenicAreaInfo();
        if (scenicAreaInfo == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景区介绍不能为空");
        }
        Double scenicAreaLongitude = addScenicAreaRequest.getScenicAreaLongitude();
        if (scenicAreaLongitude == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景区经度不能为空");
        }
        Double scenicAreaLatitude = addScenicAreaRequest.getScenicAreaLatitude();
        if (scenicAreaLatitude == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景区纬度不能为空");
        }
        MultipartFile scenicAreaVoice = addScenicAreaRequest.getScenicAreaVoice();
        if (scenicAreaVoice == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景区语音不能为空");
        }
        MultipartFile[] scenicAreaImages = addScenicAreaRequest.getScenicAreaImages();
        if (scenicAreaImages == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景区图片不能为空");
        }

        ScenicArea scenicArea = new ScenicArea();
        scenicArea.setScenicAreaName(scenicAreaName);
        scenicArea.setScenicAreaInfo(scenicAreaInfo);
        scenicArea.setScenicAreaLongitude(scenicAreaLongitude);
        scenicArea.setScenicAreaLatitude(scenicAreaLatitude);

        scenicArea.setScenicAreaVoice(qiniuUtil.upload(scenicAreaVoice));

        //上传图片
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : scenicAreaImages){
            fileNames.add(qiniuUtil.upload(file));
        }
        Gson gson = new Gson();
        String jsonFileNames = gson.toJson(fileNames);


        scenicArea.setScenicAreaImage(jsonFileNames);


        this.save(scenicArea);
        return scenicArea.getId();
    }

    @Override
    public Page<ScenicAreaVO> searchScenicAreaListByPage(SearchScenicAreaRequest searchScenicAreaRequest) {

        QueryWrapper<ScenicArea> queryWrapper = new QueryWrapper<>();

        //条件组合查询
        Long id = searchScenicAreaRequest.getId();
        if (id != null){
            queryWrapper.lambda().eq(ScenicArea::getId, id);
        }
        String scenicAreaName = searchScenicAreaRequest.getScenicAreaName();
        if (scenicAreaName != null){
            queryWrapper.lambda().like(ScenicArea::getScenicAreaName, scenicAreaName);
        }
        Double scenicAreaLongitude = searchScenicAreaRequest.getScenicAreaLongitude();
        if (scenicAreaLongitude != null){
            queryWrapper.lambda().eq(ScenicArea::getScenicAreaLongitude, scenicAreaLongitude);
        }
        Double scenicAreaLatitude = searchScenicAreaRequest.getScenicAreaLatitude();
        if (scenicAreaLatitude != null){
            queryWrapper.lambda().eq(ScenicArea::getScenicAreaLatitude, scenicAreaLatitude);
        }

        //分页查询
        int pageNum = searchScenicAreaRequest.getPageNum();
        int pageSize = searchScenicAreaRequest.getPageSize();
        Page<ScenicArea> page = new Page<>(pageNum, pageSize);
        Page<ScenicArea> scenicAreaPage = this.page(page, queryWrapper);

        //转换
        Page<ScenicAreaVO> scenicAreaVOPage = new Page<>(scenicAreaPage.getCurrent(), scenicAreaPage.getSize(), scenicAreaPage.getTotal());
        List<ScenicArea> scenicAreaList = scenicAreaPage.getRecords();
        List<ScenicAreaVO> scenicAreaVOList = new ArrayList<>();
        scenicAreaList.forEach(scenicArea -> {
            ScenicAreaVO scenicAreaVO = new ScenicAreaVO();
            Gson gson = new Gson();
            scenicAreaVO.setScenicAreaImages(gson.fromJson(scenicArea.getScenicAreaImage(), new TypeToken<List<String>>(){}.getType()));
            BeanUtils.copyProperties(scenicArea, scenicAreaVO);
            scenicAreaVOList.add(scenicAreaVO);
        });
        scenicAreaVOPage.setRecords(scenicAreaVOList);
        return scenicAreaVOPage;
    }

    @Override
    public Boolean updateScenicArea(UpdateScenicAreaRequest updateScenicAreaRequest) {

        Long id = updateScenicAreaRequest.getId();
        System.out.println("id:========================================== " + id);
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }

        QueryWrapper<ScenicArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ScenicArea::getId, id);
        ScenicArea dbScenicArea = this.getOne(queryWrapper);
        if (dbScenicArea == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景区不存在");
        }

        ScenicArea scenicArea = new ScenicArea();
        BeanUtils.copyProperties(updateScenicAreaRequest, scenicArea);
        if (updateScenicAreaRequest.getScenicAreaVoice()!=null){
            qiniuUtil.deleteFile(dbScenicArea.getScenicAreaVoice());
            scenicArea.setScenicAreaVoice(qiniuUtil.upload(updateScenicAreaRequest.getScenicAreaVoice()));
        }
        return this.updateById(scenicArea);


    }

    @Override
    public Integer deleteScenicArea(List<Long> ids) {
        //校验id是否合法
        DeleteUtil.checkId(ids);
        return this.baseMapper.deleteBatchIds(ids);
    }

    @Override
    public Boolean updateScenicAreaImage(UpdateScenicAreaImageRequest updateScenicAreaImageRequest) {
        Long id = updateScenicAreaImageRequest.getId();
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        QueryWrapper<ScenicArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ScenicArea::getId, id);
        ScenicArea dbScenicArea = this.getOne(queryWrapper);
        if (dbScenicArea == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景区不存在");
        }
        Long imageIndex = updateScenicAreaImageRequest.getIndex();
        if (imageIndex == null || imageIndex < 0){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        String scenicAreaImage = dbScenicArea.getScenicAreaImage();
        Gson gson = new Gson();
        List<String> imageList = gson.fromJson(scenicAreaImage, new TypeToken<List<String>>(){}.getType());
        qiniuUtil.deleteFile(imageList.get(imageIndex.intValue()));
        imageList.set(imageIndex.intValue(), qiniuUtil.upload(updateScenicAreaImageRequest.getScenicAreaImage()));
        String jsonScenicAreaImage = gson.toJson(imageList);
        return this.update().set("scenicAreaImage", jsonScenicAreaImage).eq("id", id).update();

    }

    @Override
    public Boolean deleteScenicAreaImage(DeleteScenicAreaImageRequest deleteScenicAreaImageRequest) {
        Long id = deleteScenicAreaImageRequest.getId();
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        QueryWrapper<ScenicArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ScenicArea::getId, id);
        ScenicArea dbScenicArea = this.getOne(queryWrapper);
        if (dbScenicArea == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "景区不存在");
        }
        Long imageIndex = deleteScenicAreaImageRequest.getIndex();
        if (imageIndex == null || imageIndex < 0){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        String scenicAreaImage = dbScenicArea.getScenicAreaImage();
        Gson gson = new Gson();
        List<String> imageList = gson.fromJson(scenicAreaImage, new TypeToken<List<String>>(){}.getType());
        qiniuUtil.deleteFile(imageList.get(imageIndex.intValue()));
        imageList.remove(imageIndex.intValue());
        String jsonScenicAreaImage = gson.toJson(imageList);
        return this.update().set("scenicAreaImage", jsonScenicAreaImage).eq("id", id).update();


    }

    @Override
    public Boolean addScenicAreaImage(AddScenicAreaImageRequest addScenicAreaImageRequest) {
        Long id = addScenicAreaImageRequest.getId();
        if (id == null || id <= 0){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        MultipartFile scenicAreaImage = addScenicAreaImageRequest.getScenicAreaImage();
        if (scenicAreaImage == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "上传图片为空");
        }
        QueryWrapper<ScenicArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ScenicArea::getId, id);
        ScenicArea dbScenicArea = this.getOne(queryWrapper);
        String dbScenicAreaScenicAreaImage = dbScenicArea.getScenicAreaImage();
        Gson gson = new Gson();
        List<String> imageList = gson.fromJson(dbScenicAreaScenicAreaImage, new TypeToken<List<String>>(){}.getType());
        imageList.add(qiniuUtil.upload(scenicAreaImage));
        String jsonScenicAreaImage = gson.toJson(imageList);
        return this.update().set("scenicAreaImage", jsonScenicAreaImage).eq("id", id).update();
    }
}




