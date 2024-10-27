package com.xudu.culturaltravelbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.exception.ServiceException;
import com.xudu.culturaltravelbackend.model.dto.elementdto.AddElementRequest;
import com.xudu.culturaltravelbackend.model.dto.elementdto.SearchElementRequest;
import com.xudu.culturaltravelbackend.model.dto.elementdto.UpdateElementRequest;
import com.xudu.culturaltravelbackend.model.entity.Element;
import com.xudu.culturaltravelbackend.model.entity.Tag;
import com.xudu.culturaltravelbackend.model.vo.ElementVO;
import com.xudu.culturaltravelbackend.service.ElementService;
import com.xudu.culturaltravelbackend.mapper.ElementMapper;
import com.xudu.culturaltravelbackend.service.TagService;
import com.xudu.culturaltravelbackend.utils.DeleteUtil;
import com.xudu.culturaltravelbackend.utils.QiniuUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* @author xudu
* @description 针对表【element】的数据库操作Service实现
* @createDate 2024-10-27 17:21:16
*/
@Service
public class ElementServiceImpl extends ServiceImpl<ElementMapper, Element> implements ElementService{

    @Resource
    private TagService tagService;


    @Resource
    private QiniuUtil qiniuUtil;
    @Autowired
    private DeleteUtil deleteUtil;

    @Override
    public Long saveElement(AddElementRequest addElementRequest) {
        String elementName = addElementRequest.getElementName();
        if (elementName == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "名称不能为空");
        }
        MultipartFile elementImage = addElementRequest.getElementImage();
        if (elementImage == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "图片不能为空");
        }
        Long tagId = addElementRequest.getTagId();
        if (tagId == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "对应标签不能为空");
        }
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Tag::getId, tagId);
        Tag tag = tagService.getOne(queryWrapper);
        if (tag == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "对应标签不存在");
        }
        Element element = new Element();
        element.setElementName(elementName);
        element.setElementImage(qiniuUtil.upload(elementImage));
        element.setTagId(tagId);
        boolean save = this.save(element);
        if (!save){
            throw new ServiceException(ErrorCode.OPERATION_ERROR, "添加元素失败");
        }
        return element.getId();



    }

    @Override
    public List<ElementVO> searchElementList(SearchElementRequest searchElementRequest) {
        QueryWrapper<Element> queryWrapper = new QueryWrapper<>();

        //条件组合查询
        Long id = searchElementRequest.getId();
        if (id != null && id > 0){
            queryWrapper.lambda().eq(Element::getId, id);
        }
        String elementName = searchElementRequest.getElementName();
        if (elementName != null){
            queryWrapper.lambda().like(Element::getElementName, elementName);
        }
        Long tagId = searchElementRequest.getTagId();
        if (tagId != null){
            queryWrapper.lambda().eq(Element::getTagId, tagId);
        }

        List<Element> elementList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(elementList)){
            throw new ServiceException(ErrorCode.NULL_ERROR, "无搜索结果");
        }
        List<ElementVO> elementVOList = new ArrayList<>();
        elementList.forEach(element -> {
            ElementVO elementVO = new ElementVO();
            BeanUtils.copyProperties(element, elementVO);
            elementVOList.add(elementVO);
        });

        return elementVOList;

    }


    @Override
    public Boolean updateElement(UpdateElementRequest uopdateElementRequest) {
        Long id = uopdateElementRequest.getId();
        if (id == null || id <= 0){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        QueryWrapper<Element> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Element::getId, id);
        Element dbElement = this.getOne(queryWrapper);
        if (dbElement == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "元素不存在");
        }


        Element element = new Element();
        if (uopdateElementRequest.getElementImage() != null){
            qiniuUtil.deleteFile(dbElement.getElementImage());
            element.setElementImage(qiniuUtil.upload(uopdateElementRequest.getElementImage()));
        }
        BeanUtils.copyProperties(uopdateElementRequest, element);
        return this.updateById(element);



    }

    @Override
    public Integer deleteElement(List<Long> ids) {
        DeleteUtil.checkId(ids);
        return this.baseMapper.deleteBatchIds(ids);
    }
}




