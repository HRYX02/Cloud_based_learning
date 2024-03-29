package com.sxx.eduservice.controller.reception;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxx.commonutils.R;
import com.sxx.eduservice.entity.EduCourse;
import com.sxx.eduservice.entity.EduTeacher;
import com.sxx.eduservice.service.EduCourseService;
import com.sxx.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author SHIXINXI
 * @description 讲师前台接口
 * @create 2023-07-24-下午 1:13
 */
@Api(description = "前台讲师")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacherReception")
public class TeacherReceptionController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;

    /**
     * @description 分页查询讲师的方法
     */
    @ApiOperation(value = "分页查询讲师列表")
    @PostMapping("/getTeacherList/{page}/{limit}")
    public R getTeacherList(@PathVariable @ApiParam(name = "page",value = "当前页") long page , @PathVariable @ApiParam(name = "limit",value = "每页数量") long limit) {
        Page<EduTeacher> pageInfo = new Page<>(page,limit);
        Map<String, Object> map =  teacherService.getTeacherList(pageInfo);
        return R.ok().data(map);
    }

    /**
     * @description 讲师详情
     */
    @ApiOperation(value = "讲师详情")
    @GetMapping("/getTeacherInfo/{teacherId}")
    public R getTeacherInfo(@PathVariable @ApiParam(name = "teacherId",value = "讲师id") String teacherId){
        EduTeacher eduTeacher = teacherService.getById(teacherId);

        LambdaQueryWrapper<EduCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduCourse::getTeacherId,teacherId);
        List<EduCourse> courseList = courseService.list(queryWrapper);
        return R.ok().data("teacher",eduTeacher).data("courseList",courseList);
    }

}