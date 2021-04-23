package com.xiao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.dao.TRoleDao;
import com.xiao.entity.TRole;
import com.xiao.service.TRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TRoleServiceImpl.java
 * @Description
 * @createTime 2021年01月23日 23:34:00
 */
@Service
public class TRoleServiceImpl extends ServiceImpl<TRoleDao, TRole> implements TRoleService {

    @Override
    public List<TRole> getRoleByUserId(Long userId) {
        return baseMapper.getRoleByUserId(userId);
    }
}
