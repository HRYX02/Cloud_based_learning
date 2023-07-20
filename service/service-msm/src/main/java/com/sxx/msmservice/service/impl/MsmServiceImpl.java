package com.sxx.msmservice.service.impl;

import com.sxx.msmservice.service.MsmService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-20-下午 9:21
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        return false;
    }
}
