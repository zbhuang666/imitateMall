package com.travelsky.mall.imitatemallsso.util;
public class ConfigureUtil {
    /*token超时时间，30分钟后移除*/
    public final static Integer TOKEN_EXPIRE_TIME = 60 * 30;
    /*token重置时间,存储超过十分钟重置*/
    public final static Integer TOKEN_RESET_TIME = 60 * 10 * 1000;
}
