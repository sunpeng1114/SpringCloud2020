package com.jd.springcloud.controller;

import com.jd.springcloud.domain.CommonResult;
import com.jd.springcloud.service.impl.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageServiceImpl storageService;

    @RequestMapping(value = "/storage/decrease")
    CommonResult decrease(Long productId, Integer count){
        storageService.decrease(productId,count);
        return new CommonResult(200,"扣减库存成功！");
    }
}
