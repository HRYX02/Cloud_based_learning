package com.sxx.commentservice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SHIXINXI
 * @description 课程评论模块
 * @create 2023-07-28-下午 2:53
 */
@EnableFeignClients
@EnableDiscoveryClient
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.sxx"})
@MapperScan("com.sxx.commentservice.mapper")
public class CommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class,args);
        log.info("service_comment模块启动成功");
    }
}