package com.sxx.eduservice.service.impl;

import com.sxx.eduservice.entity.EduCourse;
import com.sxx.eduservice.entity.EduCourseDescription;
import com.sxx.eduservice.entity.vo.CourseInfoVo;
import com.sxx.eduservice.entity.vo.CoursePublishVO;
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

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        // 查询课程表
        EduCourse eduCourse = this.getById(courseId);
        //查询课程描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        if (courseDescription == null) {
            return courseInfoVo;
        }
        BeanUtils.copyProperties(courseDescription,courseInfoVo);
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        // 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        boolean updateByIdCourse = this.updateById(eduCourse);
        if (!updateByIdCourse) {
            throw new YunShangException(20001,"修改课程信息失败");
        }

        // 修改描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        boolean updateByIdDescription = courseDescriptionService.updateById(eduCourseDescription);
        if (!updateByIdDescription) {
            throw new YunShangException(20001,"修改课程描述失败");
        }
    }

    @Override
    public CoursePublishVO publishCourseInfo(String id) {
        CoursePublishVO publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }
}
