package com.xiao.seata.service.impl;

import com.xiao.seata.dao.AccountDao;
import com.xiao.seata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    public String decrease( Long id,  Integer money) {
        accountDao.decrease(id, money);
        return "1";
    }
}
