package com.xiao.seata.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountDao {

    void decrease(@Param("userId") Long userId, @Param("account") Integer account);
}
