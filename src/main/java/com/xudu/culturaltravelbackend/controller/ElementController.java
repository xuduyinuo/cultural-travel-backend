package com.xudu.culturaltravelbackend.controller;

import com.xudu.culturaltravelbackend.common.DeleteBatchRequest;
import com.xudu.culturaltravelbackend.common.Result;
import com.xudu.culturaltravelbackend.model.dto.elementdto.AddElementRequest;
import com.xudu.culturaltravelbackend.model.dto.elementdto.SearchElementRequest;
import com.xudu.culturaltravelbackend.model.dto.elementdto.UpdateElementRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName ElementController
 * @Description
 * @Author xudu
 * @Time 2024/10/27 17:26
 */
public interface ElementController {

    @PostMapping("/add")
    Result addElement(AddElementRequest addElementRequest);

    @PostMapping("/delete")
    Result deleteElement(@RequestBody DeleteBatchRequest deleteBatchRequest);

    @GetMapping("/search/list")
    Result searchElementList(SearchElementRequest searchElementRequest);


    @PostMapping("/update")
    Result updateElement(UpdateElementRequest updateElementRequest);


}
