package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.model.dto.elementdto.AddElementRequest;
import com.xudu.culturaltravelbackend.model.dto.elementdto.SearchElementRequest;
import com.xudu.culturaltravelbackend.model.dto.elementdto.UpdateElementRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName ElementController
 * @Description
 * @Author xudu
 * @Time 2024/10/27 17:26
 */
@Api(tags = "标签内元素模块")
public interface ElementController {

    @ApiOperation("添加元素")
    @PostMapping("/add")
    Result addElement(AddElementRequest addElementRequest);


    @ApiOperation("删除元素")
    @PostMapping("/delete")
    Result deleteElement(@RequestBody DeleteBatchRequest deleteBatchRequest);


    @ApiOperation("搜索元素列表")
    @GetMapping("/search/list")
    Result searchElementList(SearchElementRequest searchElementRequest);


    @ApiOperation("更新元素")
    @PostMapping("/update")
    Result updateElement(UpdateElementRequest updateElementRequest);

    @ApiOperation("获取和元素相关的路线列表")
    @GetMapping("/get/route/list")
    Result searchRouteListByElement(Long elementId);

}
