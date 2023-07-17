package com.sxx.vod.controller;

import com.sxx.commonutils.R;
import com.sxx.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SHIXINXI
 * @description 视频上传Controller
 * @create 2023-07-16-18:54
 */
@Api(description = "章节视频")
@RestController
@CrossOrigin
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * @description 上传视频到阿里云
     */
    @ApiOperation(value = "上传视频到阿里云")
    @PostMapping("/uploadAlYunVideo")
    public R uploadAlYunVideo(@ApiParam(name = "file",value = "章节视频") MultipartFile file) {
        // 返回上传视频ID
        String videoId = vodService.uploadVideoAlYun(file);
        return R.ok().data("videoId",videoId);
    }

    /**
     *  @description 根据视频ID删除阿里云视频
     */
    @ApiOperation(value = "删除阿里云视频")
    @DeleteMapping("/removeAlYunVideo/{id}")
    public R removeAlYunVideo(@PathVariable @ApiParam(name = "id",value = "视频ID") String id) {
        vodService.removeVideoAlYun(id);
        return R.ok();
    }

}