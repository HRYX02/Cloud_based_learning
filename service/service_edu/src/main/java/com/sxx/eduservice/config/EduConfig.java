package com.sxx.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-05-31-20:06
 */

@Configuration
@MapperScan("com.sxx.eduservice.mapper")
public class EduConfig {
}
