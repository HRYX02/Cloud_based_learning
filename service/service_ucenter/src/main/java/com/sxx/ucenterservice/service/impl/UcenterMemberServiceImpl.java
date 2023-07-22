package com.sxx.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxx.commonutils.JwtUtils;
import com.sxx.commonutils.MD5;
import com.sxx.exceptionhandler.YunShangException;
import com.sxx.ucenterservice.entity.UcenterMember;
import com.sxx.ucenterservice.entity.vo.RegisterVo;
import com.sxx.ucenterservice.mapper.UcenterMemberMapper;
import com.sxx.ucenterservice.service.UcenterMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author SxxStar
 * @since 2023-07-21
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UcenterMember member) {

        // 获取手机号密码
        String phone = member.getMobile();
        String password = member.getPassword();
        // 手机号密码非空判断
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            throw new YunShangException(20001,"手机号或密码为空");
        }

        // 判断手机号是否正确
        LambdaQueryWrapper<UcenterMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UcenterMember::getMobile,phone);
        UcenterMember mobileMember = this.getOne(queryWrapper);
        // 判断查询对象是否为空
        if (mobileMember == null) {
            throw new YunShangException(20001,"手机号不存在");
        }

        if (!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new YunShangException(20001,"密码不正确");
        }

        if(mobileMember.getIsDisabled()) {
            throw new YunShangException(20001,"用户禁用");
        }

        /*
         * 登录成功
         * 生成token字符串，使用jwt工具类
         */
        return JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册的数据
        String code = registerVo.getCode(); //验证码
        String phone = registerVo.getMobile(); //手机号
        String nickname = registerVo.getNickname(); //昵称
        String password = registerVo.getPassword(); //密码

        //非空判断
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new YunShangException(20001,"注册信息不能为空");
        }
        // 判断验证码
        // 获取redis验证码
        String redisCode = redisTemplate.opsForValue().get(phone);
        if(!code.equals(redisCode)) {
            throw new YunShangException(20001,"验证码错误");
        }

        // 判断手机号是否重复，表里面存在相同手机号不进行添加
        LambdaQueryWrapper<UcenterMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UcenterMember::getMobile,phone);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new YunShangException(20001,"该手机已经注册");
        }

        // 数据添加数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(phone);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }
}