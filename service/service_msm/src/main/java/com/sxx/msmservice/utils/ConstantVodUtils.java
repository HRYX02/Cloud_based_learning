package com.sxx.msmservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-16-20:30
 */
@Component
public class ConstantVodUtils implements InitializingBean {
    @Value("${aliyun.msm.file.keyid}")
    private String accessKeyId;
    @Value("${aliyun.msm.file.keysecret}")
    private String accessKeySecret;
    public static String KEY_ID;
    public static String KEY_SECRET;
    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID = accessKeyId;
        KEY_SECRET = accessKeySecret;
    }
}


