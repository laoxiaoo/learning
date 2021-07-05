package com.xiao.seata.controller;

import com.xiao.seata.dao.StorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Autowired
    private StorageDao storageDao;

    @PostMapping("/storage/decrease")
    public String decrease(@RequestParam("id") Long id, @RequestParam("money") Integer money) {
        storageDao.decrease(id, money);
        return "1";
    }
}
