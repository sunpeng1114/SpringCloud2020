package com.jd.springcloud.controller;

import com.jd.springcloud.entities.CommonResult;
import com.jd.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"474cdd5ba06d4cb89f14c74dee19db87"));
        hashMap.put(2L,new Payment(2L,"9755c72daca34e629dd1ee64203deaef"));
        hashMap.put(3L,new Payment(3L,"a423cf231ee04dfba63b9d7e7a74e4c7"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult(200, "from mysql,serverPort: " + serverPort, payment);
        return result;
    }
}
