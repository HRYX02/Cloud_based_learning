package com.sxx.orderservice.service.impl;

import com.sxx.orderservice.entity.Order;
import com.sxx.orderservice.mapper.OrderMapper;
import com.sxx.orderservice.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author SxxStar
 * @since 2023-07-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
