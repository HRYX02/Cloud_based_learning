package com.sxx.eduservice.service;

import com.sxx.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxx.eduservice.entity.vo.CourseInfoVo;
import com.sxx.eduservice.entity.vo.CoursePublishVO;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author SxxStar
 * @since 2023-06-12
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVO publishCourseInfo(String id);

    void removeCourse(String courseId);
}
