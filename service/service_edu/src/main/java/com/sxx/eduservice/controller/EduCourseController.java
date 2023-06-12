package com.sxx.eduservice.controller;


import com.sxx.commonutils.R;
import com.sxx.eduservice.entity.vo.CourseInfoVo;
import com.sxx.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 课程 前端控制器
 * @author SxxStar
 * @since 2023-06-12
 */
@Api(description = "课程信息")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;


    /**
     * @description 添加课程基本信息
     */
    @ApiOperation("添加课程基本信息")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@ApiParam(value = "课程基本信息") @RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);

        return R.ok().data("courseId",id);
    }
}