package com.sxx.ucenterservice.controller;


import com.sxx.commonutils.JwtUtils;
import com.sxx.commonutils.R;
import com.sxx.commonutils.orderVo.UcenterMemberOrder;
import com.sxx.ucenterservice.entity.UcenterMember;
import com.sxx.ucenterservice.entity.vo.RegisterVo;
import com.sxx.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 用户登录注册接口
 * @author SxxStar
 * @since 2023-07-21
 */
@Api(description = "登录注册")
@Slf4j
@CrossOrigin
@RequestMapping("/ucenterservice/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    /**
     * @description 用户登录
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public R loginUser(@RequestBody @ApiParam(name = "member",value = "用户信息") UcenterMember member){
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    /**
     * @description 用户注册
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public R registerUser(@RequestBody @ApiParam(name = "registerVo",value = "用户信息") RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    /**
     * @description 根据token获取用户信息
     */
    @ApiOperation(value = "根据token获取用户信息")
    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        // 调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        log.info(member.toString());
        return R.ok().data("userInfo",member);
    }


    /**
     * @description 根据用户ID获取用户信息(供远程调用)
     */
    @ApiOperation(value = "根据id获取用户信息")
    @PostMapping("/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable @ApiParam(name = "id",value = "用户ID") String id) {
        UcenterMember member = memberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    /**
     * @description 查询某一天注册人数
     */
    @ApiOperation("查询某一天注册人数")
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return R.ok().data("countRegister",count);
    }
}