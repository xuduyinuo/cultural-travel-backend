package com.xudu.culturaltravelbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.exception.ServiceException;
import com.xudu.culturaltravelbackend.mapper.ElementMapper;
import com.xudu.culturaltravelbackend.model.dto.tagdto.SearchTagRequest;
import com.xudu.culturaltravelbackend.model.dto.tagdto.UpdateTagRequest;
import com.xudu.culturaltravelbackend.model.entity.Element;
import com.xudu.culturaltravelbackend.model.entity.Tag;
import com.xudu.culturaltravelbackend.model.vo.TagVO;
import com.xudu.culturaltravelbackend.service.ElementService;
import com.xudu.culturaltravelbackend.service.TagService;
import com.xudu.culturaltravelbackend.mapper.TagMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author xudu
* @description 针对表【tag】的数据库操作Service实现
* @createDate 2024-10-24 23:10:08
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService{

    @Resource
    private ElementMapper elementMapper;


    @Override
    public Long addTag(String tagName) {
        //校验名称

        Tag tag = new Tag();
        tag.setTagName(tagName);
        boolean save = this.save(tag);

        if (!save){
            throw new ServiceException(ErrorCode.SYSTEM_ERROR, "添加标签失败");
        }
        return tag.getId();
    }

    @Override
    public Integer deleteTag(List<Long> ids) {
        //删除tag之前 需要判断该标签下是否存在element，如果存在，需要把该标签下的element一起删除
        //校验id是否合法
        if (CollectionUtils.isEmpty(ids)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        ids.forEach(id -> {
            if (id <= 0) {
                throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
            }
            QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(Tag::getId, id);
            Tag tag = this.getOne(queryWrapper);
            if (tag == null){
                throw new ServiceException(ErrorCode.PARAMS_ERROR, id + "标签不存在");
            }
        });

        //删除tag关联的元素
        QueryWrapper<Element> queryWrapper = new QueryWrapper<>();
        for (Long id : ids){
            queryWrapper.lambda().eq(Element::getTagId, id);
            List<Element> elementList = elementMapper.selectList(queryWrapper);
            if (CollectionUtils.isEmpty(elementList)){
                continue;
            }
            int count = elementMapper.delete(queryWrapper);
            if (count == 0){
                throw new ServiceException(ErrorCode.SYSTEM_ERROR, "删除元素失败");
            }

        }

        return this.baseMapper.deleteBatchIds(ids);

    }

    @Override
    public List<TagVO> searchTagList(SearchTagRequest searchTagRequest) {

        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();

        //条件组合查询
        Long id = searchTagRequest.getId();
        if (id != null && id > 0){
            queryWrapper.lambda().eq(Tag::getId, id);
        }
        String tagName = searchTagRequest.getTagName();
        if (StringUtils.isNotBlank(tagName)){
            queryWrapper.lambda().like(Tag::getTagName, tagName);
        }

        List<Tag> tagList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(tagList)){
            throw new ServiceException(ErrorCode.NULL_ERROR, "无搜索结果");
        }
        ArrayList<TagVO> tagVOList = new ArrayList<>();
        tagList.forEach(tag -> {
            TagVO tagVO = new TagVO();
            BeanUtils.copyProperties(tag, tagVO);
            tagVOList.add(tagVO);
        });
        return tagVOList;
    }

    @Override
    public Boolean updateTag(UpdateTagRequest updateTagRequest) {

        Long id = updateTagRequest.getId();
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Tag::getId, id);
        Tag dbTag = this.getOne(queryWrapper);
        if (dbTag == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "标签不存在");
        }
        Tag tag = new Tag();
        BeanUtils.copyProperties(updateTagRequest, tag);
        return this.updateById(tag);
    }
}




