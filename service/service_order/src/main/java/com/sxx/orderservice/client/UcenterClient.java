package com.sxx.orderservice.client;

import com.sxx.commonutils.orderVo.UcenterMemberOrder;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-29-下午 1:37
 */

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @PostMapping("/ucenterservice/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") @ApiParam(name = "id",value = "用户ID") String id);
}
