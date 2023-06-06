package com.sxx.eduservice.controller;

import com.sxx.commonutils.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author SHIXINXI
 * @CrossOrigin 解决跨域访问注解
 * @description 后台管理登录
 * @create 2023-06-06-11:01
 */

@Api(description = "讲师登录")
@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/eduservice/user")
public class EduLoginController {

    /**
     * @description 登录
     */
    @PostMapping("/login")
    public R login() {

        return R.ok().data("token","admin");
    }

    /**
     * @description 登录
     */
    @GetMapping("/info")
    public R info() {

        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
