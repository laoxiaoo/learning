package com.xiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.entity.TRole;

import java.util.List;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TRoleService.java
 * @Description
 * @createTime 2021年01月23日 23:33:00
 */
public interface TRoleService extends IService<TRole> {

    /**
     * 根据用户id获取角色
     * @param userId
     * @return
     */
    List<TRole> getRoleByUserId(Long userId);
}
