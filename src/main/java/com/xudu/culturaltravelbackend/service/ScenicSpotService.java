package com.xudu.culturaltravelbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.AddScenicAreaImageRequest;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.DeleteScenicAreaImageRequest;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.UpdateScenicAreaImageRequest;
import com.xudu.culturaltravelbackend.model.dto.scenicSpotdto.*;
import com.xudu.culturaltravelbackend.model.entity.ScenicSpot;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xudu.culturaltravelbackend.model.vo.ScenicSpotVO;

import java.util.List;

/**
* @author xudu
* @description 针对表【scenic_spot】的数据库操作Service
* @createDate 2024-10-27 15:01:04
*/
public interface ScenicSpotService extends IService<ScenicSpot> {

    /**
     * 添加景区
     * @param addScenicSpotRequest
     * @return 返回新增的景区id
     */
    Long saveScenicSpot(AddScenicSpotRequest addScenicSpotRequest);

    /**
     * 删除景区
     * @param ids
     * @return 删除成功的数量
     */
    Integer deleteScenicSpot(List<Long> ids);

    /**
     * 更新景区信息（除图片）
     * @param updateScenicSpotRequest
     * @return
     */
    Boolean updateScenicSpot(UpdateScenicSpotRequest updateScenicSpotRequest);

    /**
     * 查询景点
     * @param searchScenicSpotRequest
     * @return 返回分页结果
     */
    Page<ScenicSpotVO> searchScenicSpotListByPage(SearchScenicSpotRequest searchScenicSpotRequest);


    /**
     * 更新景点图片
     * @param updateScenicSpotImageRequest
     * @return
     */
    Boolean updateScenicSpotImage(UpdateScenicSpotImageRequest updateScenicSpotImageRequest);

    /**
     * 删除景点图片
     * @param deleteScenicSpotImageRequest
     * @return
     */
    Boolean deleteScenicSpotImage(DeleteScenicSpotImageRequest deleteScenicSpotImageRequest);

    /**
     * 新增景点图片
     * @param addScenicSpotImageRequest
     * @return
     */
    Boolean addScenicSpotImage(AddScenicSpotImageRequest addScenicSpotImageRequest);

}
