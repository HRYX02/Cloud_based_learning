package com.sxx.eduservice.controller;


import com.sxx.commonutils.R;
import com.sxx.eduservice.entity.chapter.ChapterVO;
import com.sxx.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author SxxStar
 * @since 2023-06-12
 */
@Api(description = "课程大纲")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    /**
     * @description 课程大纲列表,根据课程id进行查询
     */
    @ApiOperation(value = "课程大纲")
    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(@ApiParam("课程ID") @PathVariable String courseId) {
        List<ChapterVO> list =  chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }
}

