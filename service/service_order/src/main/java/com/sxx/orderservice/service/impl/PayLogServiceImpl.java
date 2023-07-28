package com.sxx.orderservice.service.impl;

import com.sxx.orderservice.entity.PayLog;
import com.sxx.orderservice.mapper.PayLogMapper;
import com.sxx.orderservice.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author SxxStar
 * @since 2023-07-28
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
