package com.sxx.statisticsservice;

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
 * @create 2023-07-29-下午 5:59
 */
@EnableFeignClients
@EnableDiscoveryClient
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.sxx"})
@MapperScan("com.sxx.statisticsservice.mapper")
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
        log.info("service_statistics模块启动成功");
    }
}
