package com.sxx.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SHIXINXI
 * @description 自定义异常类
 * @create 2023-06-05-9:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YunShangException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    /**
     * @description 异常信息
     */
    private String msg;
}
