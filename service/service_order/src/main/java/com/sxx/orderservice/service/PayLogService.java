package com.sxx.orderservice.service;

import com.sxx.orderservice.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author SxxStar
 * @since 2023-07-28
 */
public interface PayLogService extends IService<PayLog> {

    Map createNatvie(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrdersStatus(Map<String, String> map);
}
