package com.sxx.exceptionhandler;

import com.sxx.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author SHIXINXI
 * @description 统一异常处理类
 * @create 2023-06-03-16:20
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @description 指定出现什么异常执行该方法
     * @ExceptionHandler() 指定要处理的异常
     * @ResponseBody 返回json数据
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception exception) {
        exception.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

}
