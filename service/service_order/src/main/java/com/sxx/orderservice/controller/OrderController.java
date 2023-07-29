package com.sxx.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sxx.commonutils.JwtUtils;
import com.sxx.commonutils.R;
import com.sxx.orderservice.entity.Order;
import com.sxx.orderservice.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author SxxStar
 * @since 2023-07-28
 */
@Api(description = "订单")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/orderservice/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * @description 生成订单方法
     */
    @ApiOperation(value = "生成订单返回订单号")
    @PostMapping("/createOrder/{courseId}")
    public R createOrder(@PathVariable @ApiParam(name = "courseId",value = "课程ID") String courseId,HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderId = orderService.createOrders(courseId,memberId);
        return R.ok().data("orderId",orderId);
    }

    /**
     * @description 根据订单id查询订单信息
     */
    @ApiOperation(value = "根据订单id查询订单信息")
    @GetMapping("/getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getOrderNo,orderId);
        Order order = orderService.getOne(queryWrapper);
        return R.ok().data("order",order);
    }
}

