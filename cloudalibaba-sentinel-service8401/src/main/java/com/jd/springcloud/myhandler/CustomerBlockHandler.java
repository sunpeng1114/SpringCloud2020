package com.jd.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jd.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(444,"按客户自定义限流测试,global handlerException");

    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444,"按客户自定义限流测试,global handlerException2");
    }
}
