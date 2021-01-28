package com.jd.springcloud.controller;

import com.jd.springcloud.domain.CommonResult;
import com.jd.springcloud.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @RequestMapping(value = "/account/decrease")
    CommonResult decrease(Long userId, BigDecimal money){
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountService.decrease(userId,money);
        return new CommonResult(200,"扣减账户成功！");
    }
}
