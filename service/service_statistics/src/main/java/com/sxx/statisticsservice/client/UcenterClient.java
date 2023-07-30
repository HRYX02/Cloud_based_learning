package com.sxx.statisticsservice.client;

import com.sxx.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-30-下午 1:37
 */
@FeignClient("service-ucenter")
public interface UcenterClient {
    /**
     * @apiNote 查询某一天注册人数
     * @param day
     * @return
     */
    @GetMapping("/ucenterservice/member/countRegister/{day}")
    R countRegister(@PathVariable("day") String day);
}
