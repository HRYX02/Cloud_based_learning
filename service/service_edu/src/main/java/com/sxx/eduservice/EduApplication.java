package com.sxx.eduservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-05-31-20:01
 */
@Slf4j
@ComponentScan(basePackages = {"com.sxx"})
@SpringBootApplication
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
        log.info("项目启动成功");

    }
}
