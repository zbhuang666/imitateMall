package com.travelsky.mall.imitatemallsso.service;

import com.travelsky.mall.imitatemallsso.bean.User;

public interface UserService {
    User getUser(String username, String password);
}
