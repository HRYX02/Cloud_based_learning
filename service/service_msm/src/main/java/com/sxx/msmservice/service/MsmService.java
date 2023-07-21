package com.sxx.msmservice.service;

import java.util.Map;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-20-下午 9:20
 */
public interface MsmService {
    /**
     * @description 发送短信的方法
     */
    boolean send(Map<String, Object> param, String phone);
}
