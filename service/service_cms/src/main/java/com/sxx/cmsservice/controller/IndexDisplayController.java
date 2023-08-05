package com.sxx.cmsservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sxx.cmsservice.entity.EduCourse;
import com.sxx.cmsservice.entity.EduTeacher;
import com.sxx.cmsservice.service.EduCourseService;
import com.sxx.cmsservice.service.EduTeacherService;
import com.sxx.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SHIXINXI
 * @description 首页热门课程和名师接口
 * @create 2023-07-20-16:55
 */
@Api(description = "首页热门课程和名师接口")
@RestController
@RequestMapping("/cmsservice/indexdisplay")
public class IndexDisplayController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    /**
     * @description 查询前8条热门课程，查询前4条名师
     */
    @Cacheable(value = "index",key = "'indexHotList'")
    @ApiOperation(value = "查询入门课程老师")
    @GetMapping("/index")
    public R index() {
        LambdaQueryWrapper<EduCourse> queryWrapperCourse = new LambdaQueryWrapper<>();
        queryWrapperCourse.orderByDesc(EduCourse::getId);
        queryWrapperCourse.last("limit 8");
        List<EduCourse> courseList = courseService.list(queryWrapperCourse);

        LambdaQueryWrapper<EduTeacher> queryWrapperTeacher = new LambdaQueryWrapper<>();
        queryWrapperTeacher.orderByDesc(EduTeacher::getId);
        queryWrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(queryWrapperTeacher);
        Map<String, Object> map = new HashMap<>();
        map.put("courseList",courseList);
        map.put("teacherList",teacherList);
        return R.ok().data(map);
    }
}
