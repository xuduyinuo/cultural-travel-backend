package com.xudu.culturaltravelbackend.service;

import com.baomidou.mybatisplus.core.injector.methods.DeleteBatchByIds;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xudu.culturaltravelbackend.model.dto.scenicAreadto.*;
import com.xudu.culturaltravelbackend.model.entity.ScenicArea;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xudu.culturaltravelbackend.model.vo.ScenicAreaVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author xudu
* @description 针对表【scenic_area】的数据库操作Service
* @createDate 2024-10-26 23:25:05
*/
public interface ScenicAreaService extends IService<ScenicArea> {

    /**
     * 新增景区
     * @param addScenicAreaRequest 添加景区请求参数
     * @return 新增景区id
     */
    Long saveScenicArea(AddScenicAreaRequest addScenicAreaRequest);

    /**
     * 查询景区
     */
    Page<ScenicAreaVO> searchScenicAreaListByPage(SearchScenicAreaRequest searchScenicAreaRequest);

    /**
     * 更新景区
     * @param updateScenicAreaRequest
     * @return
     */
    Boolean updateScenicArea(UpdateScenicAreaRequest updateScenicAreaRequest);

    /**
     * 删除景区
     * @param ids
     * @return 成功删除数量
     */
    Integer deleteScenicArea(List<Long> ids);

    /**
     * 更新景区图片
     * @param updateScenicAreaImageRequest
     * @return
     */
    Boolean updateScenicAreaImage(UpdateScenicAreaImageRequest updateScenicAreaImageRequest);

    /**
     * 删除景区图片
     * @param deleteScenicAreaImageRequest
     * @return
     */
    Boolean deleteScenicAreaImage(DeleteScenicAreaImageRequest deleteScenicAreaImageRequest);

    /**
     * 新增景区图片
     * @param addScenicAreaImageRequest
     * @return
     */
    Boolean addScenicAreaImage(AddScenicAreaImageRequest addScenicAreaImageRequest);

}
