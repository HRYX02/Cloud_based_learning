package com.sxx.eduservice.controller;


import com.sxx.commonutils.R;
import com.sxx.eduservice.entity.EduVideo;
import com.sxx.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author SxxStar
 * @since 2023-06-12
 */
@Api("课程大纲-小节")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    /***
     * @desprition 添加小节
     */
    @ApiOperation("添加小节")
    @PostMapping("/addVideo")
    public R addVideo(@ApiParam("小节信息") @RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    /***
     * @desprition 删除小节
     * TODO 后面方法需要完善 删除小节同时删除视频
     */
    @ApiOperation("删除小节")
    @DeleteMapping("/{id}")
    public R deleteVideo(@ApiParam("小节ID") @PathVariable String id) {
        videoService.removeById(id);
        return R.ok();
    }

    /**
     * @description 根据id查询小节
     */
    @ApiOperation("根据id查询小节")
    @GetMapping("/getVideoInfo/{videoId}")
    public R getVideoInfo(@ApiParam("小节ID") @PathVariable String videoId) {
        EduVideo eduVideo = videoService.getById(videoId);
        return R.ok().data("video",eduVideo);
    }

    /***
     * @desprition 修改小节
     */
    @ApiOperation("修改小节")
    @PostMapping("/updateVideo")
    public R updateVideo(@ApiParam("小节信息") @RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return R.ok();
    }
}

