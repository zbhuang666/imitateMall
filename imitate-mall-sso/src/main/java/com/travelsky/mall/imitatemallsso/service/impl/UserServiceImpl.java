package com.travelsky.mall.imitatemallsso.service.impl;

import com.travelsky.mall.imitatemallsso.bean.User;
import com.travelsky.mall.imitatemallsso.mapper.UserMapper;
import com.travelsky.mall.imitatemallsso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    public User getUser(String username, String password) {
        return userMapper.getUser(username,password);
    }
}
