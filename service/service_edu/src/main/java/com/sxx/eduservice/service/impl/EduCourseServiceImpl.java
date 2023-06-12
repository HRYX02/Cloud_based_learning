package com.sxx.eduservice.service.impl;

import com.sxx.eduservice.entity.EduCourse;
import com.sxx.eduservice.entity.EduCourseDescription;
import com.sxx.eduservice.entity.vo.CourseInfoVo;
import com.sxx.eduservice.mapper.EduCourseMapper;
import com.sxx.eduservice.service.EduCourseDescriptionService;
import com.sxx.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxx.exceptionhandler.YunShangException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author SxxStar
 * @since 2023-06-12
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    /**
     * @description 添加课程基本信息
     */
    @Transactional
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        // 1 向课程表添加课程基本信息
        // CourseInfoVo转换为eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        boolean save = this.save(eduCourse);

        if (!save) {
            throw new YunShangException(20001,"添加课程信息失败");
        }
        // 2 向课程简介表添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,courseDescription);
        courseDescription.setId(eduCourse.getId());
        courseDescriptionService.save(courseDescription);
        return eduCourse.getId();
    }
}
