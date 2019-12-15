package com.travelsky.mall.imitatemallsso.mapper;

import com.travelsky.mall.imitatemallsso.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
     User getUser(@Param("username")String username, @Param("password")String password);
}
