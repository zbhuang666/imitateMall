package com.travelsky.mall.imitatemallsso.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travelsky.mall.imitatemallsso.annotation.AuthToken;
import com.travelsky.mall.imitatemallsso.bean.Token;
import com.travelsky.mall.imitatemallsso.bean.User;
import com.travelsky.mall.imitatemallsso.service.UserService;
import com.travelsky.mall.imitatemallsso.util.CodeMapper;
import com.travelsky.mall.imitatemallsso.util.ConfigureUtil;
import com.travelsky.mall.imitatemallsso.util.JsonUtil;
import com.travelsky.mall.imitatemallsso.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "/authentication", method = RequestMethod.GET)
    public String login(String username, String password) throws JsonProcessingException {
        User user = userService.getUser(username,password);
        if(user != null) {
            String md5Pass = DigestUtils.md5DigestAsHex(JsonUtil.objectToString(user).getBytes());
            redisTemplate.opsForValue().set(username,md5Pass);
            redisTemplate.expire(username, ConfigureUtil.TOKEN_EXPIRE_TIME,TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(md5Pass,username);
            redisTemplate.expire(md5Pass,ConfigureUtil.TOKEN_EXPIRE_TIME,TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(md5Pass+username,System.currentTimeMillis());
            redisTemplate.expire(md5Pass+username,ConfigureUtil.TOKEN_EXPIRE_TIME,TimeUnit.SECONDS);
            return Result.send(CodeMapper.LOGIN_SUCCESS_CODE,CodeMapper.LOGIN_SUCCESS_MESSAGE,new Token(md5Pass));
        }
        return Result.send(CodeMapper.LOGIN_FAIL_CODE,CodeMapper.LOGIN_FAIL_MESSAGE,CodeMapper.LOGIN_FAIL_MESSAGE);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @AuthToken
    public String test() throws JsonProcessingException {
        return Result.send("201","测试成功","测试成功");
    }
}
