package com.sxx.eduservice.client.impl;

import com.sxx.commonutils.R;
import com.sxx.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author SHIXINXI
 * @description 熔断机制出现后调用
 * @create 2023-07-17-22:19
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeAlYunVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
