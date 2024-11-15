package com.xudu.culturaltravelbackend.service;

import com.xudu.culturaltravelbackend.model.dto.elementdto.AddElementRequest;
import com.xudu.culturaltravelbackend.model.dto.elementdto.SearchElementRequest;
import com.xudu.culturaltravelbackend.model.dto.elementdto.UpdateElementRequest;
import com.xudu.culturaltravelbackend.model.entity.Element;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xudu.culturaltravelbackend.model.vo.ElementVO;
import com.xudu.culturaltravelbackend.model.vo.RouteVO;

import java.util.List;

/**
* @author xudu
* @description 针对表【element】的数据库操作Service
* @createDate 2024-10-27 17:21:16
*/
public interface ElementService extends IService<Element> {

    /**
     * 新增元素
     * @param addElementRequest
     * @return
     */
    Long saveElement(AddElementRequest addElementRequest);

    /**
     * 查询元素列表
     * @param searchElementRequest
     * @return
     */
    List<ElementVO> searchElementList(SearchElementRequest searchElementRequest);

    /**
     * 更新元素
     * @param uopdateElementRequest
     * @return
     */
    Boolean updateElement(UpdateElementRequest uopdateElementRequest);


    /**
     * 删除元素
     * @param ids
     * @return
     */
    Integer deleteElement(List<Long> ids);


    /**
     * 获取和标签内容相关联的路线
     */
    List<RouteVO> getRouteVOListByElement(Long elementId);
}
