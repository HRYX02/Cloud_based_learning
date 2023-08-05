package com.sxx.msmservice.controller;

import com.sxx.commonutils.R;
import com.sxx.msmservice.service.MsmService;
import com.sxx.msmservice.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author SHIXINXI
 * @description 云上学堂短信登录接口
 * @create 2023-07-20-下午 9:19
 */
@Api(description = "云上学堂短信登录接口")
@RestController
@CrossOrigin
@RequestMapping("/msmservice/msm")
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * @apiNote 发送短信接口
     */
    @ApiOperation(value = "发送短信接口")
    @GetMapping("/send/{phone}")
    public R sendMsm(@PathVariable @ApiParam(name = "phone",value = "手机号码") String phone){
        String code = redisTemplate.opsForValue().get(phone);
        if (StringUtils.isNotEmpty(code)) {
            return R.ok();
        }
        // 生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service发送短信的方法
        boolean isSend = msmService.send(param,phone);
        if(isSend) {
            //发送成功，把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }
    }
}
