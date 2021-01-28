package com.jd.springcloud.controller;

import com.jd.springcloud.service.IMessageProvide;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController

public class SendMessageController {

    @Resource
    private IMessageProvide messageProvide;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){

        return messageProvide.send();
    }
}
