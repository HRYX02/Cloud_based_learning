package com.sxx.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxx.eduservice.entity.User;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-08-05-下午 6:48
 */
public interface EduLoginService extends IService<User> {
    String login(User user);
}
