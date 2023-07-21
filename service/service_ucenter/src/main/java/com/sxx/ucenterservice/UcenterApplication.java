package com.sxx.ucenterservice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SHIXINXI
 * @description 前台用户登录注册模块
 * @create 2023-07-21-下午 9:00
 */
@EnableFeignClients
@EnableDiscoveryClient
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.sxx"})
@MapperScan("com.sxx.ucenterservice.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
        log.info("service_ucenterservice模块启动成功");
    }
}