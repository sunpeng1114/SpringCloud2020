package com.jd.springcloud.service.impl;

import com.jd.springcloud.dao.AccountDao;
import com.jd.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("------------->account-service中扣减账户开始");
        accountDao.decrease(userId,money);
        log.info("------------->account-service中扣减账户结束");
    }
}
