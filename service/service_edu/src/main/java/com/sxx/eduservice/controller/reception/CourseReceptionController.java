package com.sxx.eduservice.controller.reception;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxx.commonutils.JwtUtils;
import com.sxx.commonutils.R;
import com.sxx.commonutils.orderVo.CourseWebVoOrder;
import com.sxx.eduservice.client.OrdersClient;
import com.sxx.eduservice.entity.EduCourse;
import com.sxx.eduservice.entity.chapter.ChapterVO;
import com.sxx.eduservice.entity.subject.OneSubject;
import com.sxx.eduservice.entity.vo.reception.CourseReceptionInfoVo;
import com.sxx.eduservice.entity.vo.reception.CourseReceptionQuery;
import com.sxx.eduservice.service.EduChapterService;
import com.sxx.eduservice.service.EduCourseService;
import com.sxx.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    private EduSubjectService subjectService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private OrdersClient ordersClient;

    @ApiOperation(value = "查询课程列表")
    @PostMapping("getCourseList/{page}/{limit}")
    public R getCourseList(@PathVariable @ApiParam(name = "page",value = "当前页") long page,
                                @PathVariable @ApiParam(name = "limit",value = "每页数量") long limit,
                                @RequestBody @ApiParam(name = "courseVo",value = "条件") CourseReceptionQuery courseQuery) {

        Page<EduCourse> pageInfo = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseList(pageInfo,courseQuery);
        return R.ok().data(map);
    }

    /**
     *  @description 查询所有课程分类
     */
    @ApiOperation("课程分类列表(树形)")
    @GetMapping("/getAllSubject")
    public R getAllSubject() {
        List<OneSubject> allOneTwoSubject = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",allOneTwoSubject);
    }

    /**
     * @description 查看课程详情方法
     */
    @ApiOperation("查看课程详情方法")
    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable @ApiParam(name = "courseId",value = "课程id") String courseId, HttpServletRequest request) {
        // 根据课程id，编写sql语句查询课程信息
        CourseReceptionInfoVo courseReceptionInfoVo = courseService.getReceptionCourseInfo(courseId);

        // 根据课程id查询章节和小节
        List<ChapterVO> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        //根据课程id和用户id查询当前课程是否已经支付过了
        boolean buyCourse = ordersClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("courseWebVo",courseReceptionInfoVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
    }

    /**
     * @description 根据课程id查询课程信息
     */
    @PostMapping("/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseReceptionInfoVo courseInfo = courseService.getReceptionCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}
