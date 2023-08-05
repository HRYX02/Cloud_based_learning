package com.sxx.eduservice.controller.background;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxx.commonutils.R;
import com.sxx.eduservice.entity.EduCourse;
import com.sxx.eduservice.entity.vo.background.CourseInfoVo;
import com.sxx.eduservice.entity.vo.background.CoursePublishVO;
import com.sxx.eduservice.entity.vo.background.CourseQuery;
import com.sxx.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public R updateCourseInfo(@ApiParam("课程信息") @RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
     * @description 根据课程ID查询课程确认信息
     */
    @ApiOperation("查询课程确认信息")
    @GetMapping("/getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@ApiParam("课程ID") @PathVariable String id) {
        CoursePublishVO coursePublishVO = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVO);
    }

    /**
     * @description 课程的最终发布-修改课程状态
     */
    @ApiOperation("课程的最终发布-修改课程状态")
    @PostMapping("/publishCourse/{id}")
    public R publishCourseInfo(@ApiParam("课程ID") @PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        boolean update = courseService.updateById(eduCourse);
        return R.ok().data("data",update);
    }

    /**
     * @description 课程列表-基本实现
     */
    @ApiOperation("课程列表")
    @PostMapping("/pageCourseCondition/{current}/{limit}")
    public R getCourseList(@ApiParam(name = "current",value = "当前页") @PathVariable String current,
                           @ApiParam(name = "limit",value = "每页显示记录数") @PathVariable String limit,
                           @ApiParam(name = "courseQuery",value = "条件") @RequestBody CourseQuery courseQuery) {

        long l = Long.parseLong(current);
        long l1 = Long.parseLong(limit);
        Page<EduCourse> pageInfo = new Page<>(l,l1);
        LambdaQueryWrapper<EduCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(courseQuery.getTitle()),EduCourse::getTitle,courseQuery.getTitle());
        queryWrapper.eq(StringUtils.isNotEmpty(courseQuery.getStatus()),EduCourse::getStatus,courseQuery.getStatus());
        courseService.page(pageInfo, queryWrapper);
        long total = pageInfo.getTotal();
        List<EduCourse> records = pageInfo.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * @description 删除课程
     */
    @ApiOperation("删除课程")
    @DeleteMapping("{courseId}")
    public R deleteCourse(@ApiParam("课程Id") @PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return R.ok();
    }
}