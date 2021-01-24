package com.xiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.dao.TUserDao;
import com.xiao.entity.TUser;
import com.xiao.service.TUserService;
import org.springframework.stereotype.Service;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TUserServiceImpl.java
 * @Description
 * @createTime 2021年01月23日 22:58:00
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserDao, TUser> implements TUserService {
}
