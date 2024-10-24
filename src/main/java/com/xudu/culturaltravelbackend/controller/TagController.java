package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.DeleteRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.model.dto.tagdto.AddTagRequest;
import com.xudu.culturaltravelbackend.model.dto.tagdto.SearchTagRequest;
import com.xudu.culturaltravelbackend.model.dto.tagdto.UpdateTagRequest;
import com.xudu.culturaltravelbackend.model.entity.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @className: TagController
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-24
 */
public interface TagController {

    /**
     * 添加标签
     * @param addTagRequest 添加标签请求
     * @return 成功添加标签的id
     */
    @PostMapping("/add")
    public Result addTag(@RequestBody AddTagRequest addTagRequest);

    /**
     * 删除标签
     * @param deleteBatchRequest 删除标签id参数集合
     * @return 删除标签成功数量
     */
    @PostMapping("/delete")
    public Result deleteTag(@RequestBody DeleteBatchRequest deleteBatchRequest);

    /**
     * 更新标签
     * @param updateTagRequest 更新标签请求
     * @return 是否成功
     */
    @PostMapping("/update")
    public Result updateTag(@RequestBody UpdateTagRequest updateTagRequest);


    /**
     * 查询标签列表
     * @param searchTagRequest 搜索标签请求参数
     * @return 标签列表
     */
    @PostMapping("/search/list")
    public Result searchTagList(@RequestBody SearchTagRequest searchTagRequest);
}
