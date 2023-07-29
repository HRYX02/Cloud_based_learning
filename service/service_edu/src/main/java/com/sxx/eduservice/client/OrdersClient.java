package com.sxx.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-29-下午 5:35
 */
@Component
@FeignClient("service-order")
public interface OrdersClient {
    @GetMapping("/orderservice/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
