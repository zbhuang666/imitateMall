package com.travelsky.mall.imitatemallsso.util;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Result<T> {
    /**
     * 接口状态返回
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> String send(String code, String message, T data) throws JsonProcessingException {
        return JsonUtil.objectToString(new ResponseStatus(code,message,data));
    }
}
