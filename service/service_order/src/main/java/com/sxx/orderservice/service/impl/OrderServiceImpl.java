package com.sxx.orderservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxx.commonutils.orderVo.CourseWebVoOrder;
import com.sxx.commonutils.orderVo.UcenterMemberOrder;
import com.sxx.orderservice.client.CourseClient;
import com.sxx.orderservice.client.UcenterClient;
import com.sxx.orderservice.entity.Order;
import com.sxx.orderservice.mapper.OrderMapper;
import com.sxx.orderservice.service.OrderService;
import com.sxx.orderservice.utils.OrderIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CourseClient courseClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String memberId) {

        // 通过远程调用根据用户id获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);

        // 通过远程调用根据课程id获取课信息
        CourseWebVoOrder courseInfoOrder = courseClient.getCourseInfoOrder(courseId);

        // 创建Order对象，向order对象里面设置需要数据
        Order order = new Order();
        order.setOrderNo(OrderIdUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());

        // 订单状态(0未支付1已支付)
        order.setStatus(0);
        // 支付类型(1微信)
        order.setPayType(1);
        this.save(order);

        // 返回订单号
        return order.getOrderNo();
    }
}
