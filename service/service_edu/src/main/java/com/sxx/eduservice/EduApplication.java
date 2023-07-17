package com.sxx.eduservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-05-31-20:01
 * @ComponentScan 包扫描规则 目的扫描swagger配置类
 * @EnableDiscoveryClient 可以nacos进行注册
 * @EnableFeignClients nacos服务调用注解
 */
@EnableFeignClients
@EnableDiscoveryClient
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.sxx"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
        log.info("service_edu模块启动成功");
    }
}
