package com.jd.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jd.springcloud.entities.CommonResult;
import com.jd.springcloud.entities.Payment;
import com.jd.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL="http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")
    //@SentinelResource(value = "fallback",fallback = "handlerFallback")  //fallback只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")  //blockHandler只负责控制台配置违规
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
                            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id==4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常......");
        }else if(result.getData() == null){
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return result;
    }

    /**
     * 本例子是fallback
     * @param id
     * @return
     */
    public CommonResult<Payment> handlerFallback(@PathVariable("id") Long id, Throwable e){
        Payment payment = new Payment(id, null);
        return new CommonResult<Payment>(444,"handlerFallback 业务异常,一场内容：" + e.getMessage(),payment);
    }

    /**
     * 本例子是blockHandler
     * @param id
     * @return
     */
    public CommonResult<Payment> blockHandler(@PathVariable("id") Long id, BlockException blockException){
        Payment payment = new Payment(id, null);
        return new CommonResult<Payment>(445,"blockHandler 控制台配置违规,无此流水" + blockException.getMessage(),payment);
    }

    @Resource
    PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }


}
