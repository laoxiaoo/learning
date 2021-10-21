package com.xiao.mysql.lock;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author xiao ji hao
 * @create 2021年08月30日 07:50:00
 */
@Mapper
public interface UserItemDao {

    @Update("update user_item set user_id=#{userId} where id < #{id}")
    int update(@Param("id") Long id, @Param("userId") Long userId);
}
