package com.sxx.eduservice.mapper;

import com.sxx.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxx.eduservice.entity.vo.background.CoursePublishVO;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author SxxStar
 * @since 2023-06-12
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVO getPublishCourseInfo(String courseId);
}
