package com.travelsky.mall.usermanage.controller;

import com.travelsky.mall.usermanage.bean.User;
import com.travelsky.mall.usermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/allUser")
    public List<User> getAllUser() {
        return userService.getMailUser();
    }
}
