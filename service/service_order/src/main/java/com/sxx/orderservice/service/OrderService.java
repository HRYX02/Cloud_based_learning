package com.sxx.orderservice.service;

import com.sxx.orderservice.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author SxxStar
 * @since 2023-07-28
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberId);
}
