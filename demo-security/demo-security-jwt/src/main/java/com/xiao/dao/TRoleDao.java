package com.xiao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.entity.TRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TRoleDao.java
 * @Description
 * @createTime 2021年01月23日 23:32:00
 */
public interface TRoleDao extends BaseMapper<TRole> {

    /**
     * 获取角色集合
     * @param userId
     * @return
     */
    List<TRole> getRoleByUserId(@Param("userId") Long userId);

}
