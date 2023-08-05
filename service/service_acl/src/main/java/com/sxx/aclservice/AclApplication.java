package com.sxx.aclservice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-08-01-下午 1:18
 */
@EnableFeignClients
@EnableDiscoveryClient
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.sxx"})
@MapperScan(basePackages = {"com.sxx.aclservice.mapper"})
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class, args);
        log.info("service_acl模块启动成功");
    }
}
