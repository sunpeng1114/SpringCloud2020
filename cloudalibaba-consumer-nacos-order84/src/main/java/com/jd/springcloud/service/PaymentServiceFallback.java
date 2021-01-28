package com.jd.springcloud.service;

import com.jd.springcloud.entities.CommonResult;
import com.jd.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallback implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult(444, "服务降级返回，----PaymentServiceFallback", new Payment(id,"errorSerial"));
    }
}
