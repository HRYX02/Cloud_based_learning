package com.sxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxx.commonutils.MD5;
import com.sxx.eduservice.entity.User;
import com.sxx.eduservice.mapper.EduLoginMapper;
import com.sxx.eduservice.service.EduLoginService;
import com.sxx.exceptionhandler.YunShangException;
import org.springframework.stereotype.Service;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-08-05-下午 6:48
 */
@Service
public class EduLoginServiceImpl extends ServiceImpl<EduLoginMapper, User> implements EduLoginService{
    @Override
    public String login(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName()).eq(User::getPassword, MD5.encrypt(user.getPassword()));
        User userInfo = this.getOne(queryWrapper);
        if (userInfo == null) {
            throw new YunShangException(20001,"不存在此用户");
        }
        return userInfo.getNickName();
    }
}
