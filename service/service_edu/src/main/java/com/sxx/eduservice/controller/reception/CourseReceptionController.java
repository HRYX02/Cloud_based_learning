package com.sxx.eduservice.controller.reception;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxx.commonutils.R;
import com.sxx.eduservice.entity.EduCourse;
import com.sxx.eduservice.entity.vo.reception.CourseVo;
import com.sxx.eduservice.service.EduChapterService;
import com.sxx.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-25-上午 12:30
 */
@Api(description = "前台课程")
@RestController
@RequestMapping("/eduservice/courseReception")
@CrossOrigin
public class CourseReceptionController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;

    @ApiParam(value = "查询课程列表")
    @PostMapping("getCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable @ApiParam(name = "page",value = "当前页") long page,
                                @PathVariable @ApiParam(name = "limit",value = "每页数量") long limit,
                                @RequestBody @ApiParam(name = "courseVo",value = "条件") CourseVo courseVo) {

        Page<EduCourse> pageInfo = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseList(pageInfo,courseVo);
        return R.ok().data(map);
    }
}
