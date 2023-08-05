package com.sxx.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxx.eduservice.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-08-05-下午 7:01
 */
@Mapper
public interface EduLoginMapper extends BaseMapper<User> {
}
