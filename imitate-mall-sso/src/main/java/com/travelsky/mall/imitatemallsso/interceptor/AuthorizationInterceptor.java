package com.travelsky.mall.imitatemallsso.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.travelsky.mall.imitatemallsso.annotation.AuthToken;
import com.travelsky.mall.imitatemallsso.util.CodeMapper;
import com.travelsky.mall.imitatemallsso.util.ConfigureUtil;
import com.travelsky.mall.imitatemallsso.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate redisTemplate;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {
            String token = request.getHeader("token");
            String username = "";
            if (token != null && token != "") {
                username = (String) redisTemplate.opsForValue().get(token);
            }
            if (username != "" && username != null) {
                Long tokenBirthDay = (Long) redisTemplate.opsForValue().get(token + username);
                if(System.currentTimeMillis() - tokenBirthDay > ConfigureUtil.TOKEN_RESET_TIME) {
                    redisTemplate.expire(username, ConfigureUtil.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
                    redisTemplate.expire(token,ConfigureUtil.TOKEN_EXPIRE_TIME,TimeUnit.SECONDS);
                    redisTemplate.expire(token+username,ConfigureUtil.TOKEN_EXPIRE_TIME,TimeUnit.SECONDS);
                }
                return true;
            } else {
                JSONObject jsonObject = new JSONObject();
                PrintWriter writer = null;
                try {
                    writer = response.getWriter();
                    Result.send(CodeMapper.TOKEN_EXPIRE_CODE,CodeMapper.TOKEN_EXPIRE_MESSAGE,CodeMapper.TOKEN_EXPIRE_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
                return false;
            }
        }
        return true;
    }
}
