package com.jd.springcloud.service.impl;

import com.jd.springcloud.dao.OrderDao;
import com.jd.springcloud.domain.Order;
import com.jd.springcloud.service.AccountService;
import com.jd.springcloud.service.OrderService;
import com.jd.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
 */
@Service
@Slf4j

public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "zwy-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("--------->开始新建订单");
        orderDao.create(order);

        log.info("--------->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("--------->订单微服务开始调用库存，做扣减end");

        log.info("--------->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("--------->订单微服务开始调用账户，做扣减end");

        log.info("--------->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("--------->修改订单状态结束");

        log.info("--------->下订单结束了，O(∩_∩)O哈哈~");
    }
}
