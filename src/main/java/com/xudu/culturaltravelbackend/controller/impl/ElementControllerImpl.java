package com.xudu.culturaltravelbackend.controller.impl;

import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.controller.ElementController;
import com.xudu.culturaltravelbackend.model.dto.elementdto.AddElementRequest;
import com.xudu.culturaltravelbackend.model.dto.elementdto.SearchElementRequest;
import com.xudu.culturaltravelbackend.model.dto.elementdto.UpdateElementRequest;
import com.xudu.culturaltravelbackend.model.vo.ElementVO;
import com.xudu.culturaltravelbackend.service.ElementService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ElementControllerImpl
 * @Description
 * @Author xudu
 * @Time 2024/10/27 17:26
 */
@CrossOrigin
@RequestMapping("/element")
@RestController
public class ElementControllerImpl implements ElementController {

    @Resource
    private ElementService elementService;



    @Override
    public Result addElement(AddElementRequest addElementRequest) {
        Long id = elementService.saveElement(addElementRequest);
        return Result.success(id);
    }

    @Override
    public Result deleteElement(DeleteBatchRequest deleteBatchRequest) {
        List<Long> ids = deleteBatchRequest.getIds();
        Integer i = elementService.deleteElement(ids);
        return Result.success(i);
    }

    @Override
    public Result searchElementList(SearchElementRequest searchElementRequest) {
        List<ElementVO> elementVOList = elementService.searchElementList(searchElementRequest);
        return Result.success(elementVOList);
    }

    @Override
    public Result updateElement(UpdateElementRequest updateElementRequest) {
        Boolean b = elementService.updateElement(updateElementRequest);
        if (!b){
            return Result.error(ErrorCode.OPERATION_ERROR, "更新失败");
        }
        return Result.success("更新成功");
    }
}
