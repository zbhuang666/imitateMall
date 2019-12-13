package com.travelsky.mall.imitatemallsso.mapper;

import com.travelsky.mall.imitatemallsso.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
     User getUser(String username, String password);
}
