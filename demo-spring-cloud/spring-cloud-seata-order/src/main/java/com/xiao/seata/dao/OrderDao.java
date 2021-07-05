package com.xiao.seata.dao;

import com.xiao.seata.entity.TOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    void insert(@Param("order") TOrder order);

    void updateStatus(@Param("id") Long id);
}
