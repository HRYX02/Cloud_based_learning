package com.sxx.oss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SHIXINXI
 * @description 阿里云对象存储启动类，文件上传
 * exclude = DataSourceAutoConfiguration.class忽略数据源
 * @create 2023-06-07-16:41
 */
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.sxx"})
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
        log.info("service_oss模块启动成功");

    }
}