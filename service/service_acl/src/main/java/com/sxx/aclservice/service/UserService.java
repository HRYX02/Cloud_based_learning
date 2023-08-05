package com.sxx.aclservice.service;

import com.sxx.aclservice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @description 用户表
 * @author testjava
 * @since 2020-01-12
 */
public interface UserService extends IService<User> {

    /**
     * @description 从数据库中取出用户信息
     * @param username
     * @return
     */
    User selectByUsername(String username);
}
