package com.xudu.culturaltravelbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.AddScenicAreaRequest;
import com.xudu.culturaltravelbackend.model.entity.ScenicArea;
import com.xudu.culturaltravelbackend.service.ScenicAreaService;
import com.xudu.culturaltravelbackend.mapper.ScenicAreaMapper;
import com.xudu.culturaltravelbackend.utils.QiniuUtil;
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


    @Override
    public Long saveScenicArea(AddScenicAreaRequest addScenicAreaRequest) {
        MultipartFile[] scenicAreaImages = addScenicAreaRequest.getScenicAreaImages();
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : scenicAreaImages){
            fileNames.add(qiniuUtil.upload(file));
        }
        Gson gson = new Gson();
        String jsonFileNames = gson.toJson(fileNames);
        ScenicArea scenicArea = new ScenicArea();
        scenicArea.setScenicAreaName(addScenicAreaRequest.getScenicAreaName());
        scenicArea.setScenicAreaInfo(addScenicAreaRequest.getScenicAreaInfo());
        scenicArea.setScenicAreaLongitude(addScenicAreaRequest.getScenicAreaLongitude());
        scenicArea.setScenicAreaLatitude(addScenicAreaRequest.getScenicAreaLatitude());
        scenicArea.setScenicAreaImage(jsonFileNames);

        this.save(scenicArea);
        return scenicArea.getId();
    }
}




