package com.xiao.mysql.lock;

import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiao ji hao
 * @create 2021年08月30日 08:07:00
 */
@Service
public class UserItemService {

    @Autowired
    private UserItemDao userItemDao;

    @Transactional
    public void update() {
        try {
            Thread.sleep(RandomUtil.randomInt(2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userItemDao.update(9L, RandomUtil.randomLong());
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userItemDao.update(9L, RandomUtil.randomLong());
    }
}
