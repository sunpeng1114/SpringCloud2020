package com.jd.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value(value = "${server.port}")
    private String serverPort;
    @Value(value = "${config.info}")
    private String info;

    @GetMapping("/getInfo")
    public String getInfo(){
        return "serverPort:" + serverPort + "\t\n\n configInfo" + info;
    }
}
