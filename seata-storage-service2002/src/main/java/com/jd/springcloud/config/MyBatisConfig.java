package com.jd.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.jd.springcloud.dao.OrderDao")
public class MyBatisConfig {

}
