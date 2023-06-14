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

    /**
     * @description 根据课程id进行查询课程基本信息
     */
    @ApiOperation("查询课程基本信息")
    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfo(@ApiParam("课程ID") @PathVariable String courseId){
        CourseInfoVo courseInfoVo =  courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    /**
     * @description 修改课程信息
     */
    @ApiOperation("修改课程信息")
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@ApiParam("课程信息")@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }
}