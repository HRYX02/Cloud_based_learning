package com.sxx.cmsservice;

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
 * @create 2023-07-18-19:02
 */
@EnableFeignClients
@EnableDiscoveryClient
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.sxx"})
@MapperScan("com.sxx.cmsservice.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
        log.info("service_cms模块启动成功");
    }
}
