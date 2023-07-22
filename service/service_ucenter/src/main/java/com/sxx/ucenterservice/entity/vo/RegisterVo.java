package com.sxx.ucenterservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author SHIXINXI
 * @description 注册实体
 * @create 2023-07-22-下午 1:34
 */

@Data
public class RegisterVo {
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String code;
}