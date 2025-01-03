package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.annotation.AuthCheck;
import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.DeleteRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.constant.UserConstant;
import com.xudu.culturaltravelbackend.model.dto.tagdto.AddTagRequest;
import com.xudu.culturaltravelbackend.model.dto.tagdto.SearchTagRequest;
import com.xudu.culturaltravelbackend.model.dto.tagdto.UpdateTagRequest;
import com.xudu.culturaltravelbackend.model.entity.Tag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @className: TagController
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-24
 */
@Api(tags = "标签模块")
public interface TagController {

    /**
     * 添加标签
     * @param addTagRequest 添加标签请求
     * @return 成功添加标签的id
     */
    @ApiOperation("添加标签")
    @PostMapping("/add")
    Result addTag(@RequestBody AddTagRequest addTagRequest);

    /**
     * 删除标签
     * @param deleteBatchRequest 删除标签id参数集合
     * @return 删除标签成功数量
     */
    @ApiOperation("删除标签")
    @PostMapping("/delete")
    Result deleteTag(@RequestBody DeleteBatchRequest deleteBatchRequest);

    /**
     * 更新标签
     * @param updateTagRequest 更新标签请求
     * @return 是否成功
     */
    @ApiOperation("更新标签")
    @PostMapping("/update")
    Result updateTag(@RequestBody UpdateTagRequest updateTagRequest);


    /**
     * 查询标签列表
     * @param searchTagRequest 搜索标签请求参数
     * @return 标签列表
     */
    @ApiOperation("查询标签列表")
    @GetMapping("/search/list")
    Result searchTagList(SearchTagRequest searchTagRequest);
}
