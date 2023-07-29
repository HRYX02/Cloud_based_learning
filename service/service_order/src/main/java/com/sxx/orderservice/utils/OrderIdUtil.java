package com.sxx.orderservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author SHIXINXI
 * @description 生成订单号
 * @create 2023-07-29-下午 1:46
 */
public class OrderIdUtil {
    /**
     * 获取订单号
     * @return
     */
    public static String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }
}