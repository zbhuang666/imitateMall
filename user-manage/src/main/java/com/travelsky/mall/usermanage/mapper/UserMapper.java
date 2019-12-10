package com.travelsky.mall.usermanage.mapper;

import com.travelsky.mall.usermanage.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> getAllUser();
}
