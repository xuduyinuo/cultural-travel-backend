package com.xudu.culturaltravelbackend.controller.impl;


import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.controller.TagController;
import com.xudu.culturaltravelbackend.model.dto.tagdto.AddTagRequest;
import com.xudu.culturaltravelbackend.model.dto.tagdto.SearchTagRequest;
import com.xudu.culturaltravelbackend.model.dto.tagdto.UpdateTagRequest;
import com.xudu.culturaltravelbackend.service.TagService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import java.util.List;

/**
 * @className: TagControllerImpl
 * @description: TODO
 * @author: xudu
 * @create: 2024-10-24
 */

@CrossOrigin
@RequestMapping("/tag")
@RestController
public class TagControllerImpl implements TagController {

    @Resource
    private TagService tagService;


    @Override
    public Result addTag(AddTagRequest addTagRequest) {
        String tagName = addTagRequest.getTagName();
        Long tagId = tagService.addTag(tagName);
        return Result.success(tagId);
    }


    @Override
    public Result deleteTag(DeleteBatchRequest deleteBatchRequest) {
        List<Long> ids = deleteBatchRequest.getIds();
        Integer deleteCount = tagService.deleteTag(ids);
        return Result.success(deleteCount);
    }


    @Override
    public Result updateTag(UpdateTagRequest updateTagRequest) {
        Boolean b = tagService.updateTag(updateTagRequest);
        if (!b){
            return Result.error(ErrorCode.PARAMS_ERROR, "更新失败");
        }
        return Result.success("更新成功");
    }

    @Override
    public Result searchTagList(SearchTagRequest searchTagRequest) {
        return Result.success(tagService.searchTagList(searchTagRequest));
    }
}
