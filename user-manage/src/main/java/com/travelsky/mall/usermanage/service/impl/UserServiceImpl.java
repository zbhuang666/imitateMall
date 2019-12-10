package com.travelsky.mall.usermanage.service.impl;

import com.travelsky.mall.usermanage.bean.User;
import com.travelsky.mall.usermanage.mapper.UserMapper;
import com.travelsky.mall.usermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getMailUser() {
        return userMapper.getAllUser();
    }
}
