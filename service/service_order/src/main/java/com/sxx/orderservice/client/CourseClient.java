package com.sxx.orderservice.client;

import com.sxx.commonutils.orderVo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-29-下午 1:36
 */
@Component
@FeignClient("service-edu")
public interface CourseClient {

    @PostMapping("/eduservice/courseReception/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);
}
