package com.xiao.seata.controller;

import com.xiao.seata.dao.AccountDao;
import com.xiao.seata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/decrease")
    public String decrease(@RequestParam("id") Long id, @RequestParam("money") Integer money) {
        accountService.decrease(id, money);
        int i=1/0;
        return "1";
    }
}
