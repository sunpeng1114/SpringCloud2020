package com.jd.springcloud.service.impl;

import com.jd.springcloud.service.IMessageProvide;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 操作的是Rabbitmq，不是数据库没有dao
 * 使用的是@EnableBinding(Source.class)和MessageChannel套装组件
 */
@EnableBinding(Source.class)    //定义消息的推送管道
public class MessageProvideImpl implements IMessageProvide {


    @Resource
    private MessageChannel output;  //消息发送管道
    @Override
    public String send() {

        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("****serial: " + serial);
        return null;
    }
}
