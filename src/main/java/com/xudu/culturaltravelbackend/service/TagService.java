package com.xudu.culturaltravelbackend.service;

import com.xudu.culturaltravelbackend.model.dto.tagdto.SearchTagRequest;
import com.xudu.culturaltravelbackend.model.dto.tagdto.UpdateTagRequest;
import com.xudu.culturaltravelbackend.model.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xudu.culturaltravelbackend.model.vo.TagVO;

import java.util.List;

/**
* @author xudu
* @description 针对表【tag】的数据库操作Service
* @createDate 2024-10-24 23:10:08
*/
public interface TagService extends IService<Tag> {

    Long addTag(String tagName);

    Integer deleteTag(List<Long> ids);

    List<TagVO> searchTagList(SearchTagRequest searchTagRequest);

    Boolean updateTag(UpdateTagRequest updateTagRequest);



}
