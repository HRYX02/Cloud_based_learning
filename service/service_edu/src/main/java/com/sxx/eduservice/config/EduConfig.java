package com.sxx.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-05-31-20:06
 */

@Configuration
@MapperScan("com.sxx.eduservice.mapper")
public class EduConfig {

    /**
     * @description 逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }
}
