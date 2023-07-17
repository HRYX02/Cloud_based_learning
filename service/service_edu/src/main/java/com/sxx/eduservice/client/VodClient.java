package com.sxx.eduservice.client;

import com.sxx.commonutils.R;
import com.sxx.eduservice.client.impl.VodFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author SHIXINXI
 * @description 调用vod模块的客户端
 * @create 2023-07-17-17:19
 * @FeignClient("service-vod") 指定从哪个服务中调用功能
 */
@Component
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VodClient {

    /**
     * 定义调用的方法路径
     * @description 根据视频id删除阿里云视频
     */
    @DeleteMapping("/eduvod/video/removeAlYunVideo/{id}")
    R removeAlYunVideo(@PathVariable String id);

    /**
     * @description 删除多个阿里云视频的方法
     */
    @DeleteMapping("/eduvod/video/delete-batch")
    R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
