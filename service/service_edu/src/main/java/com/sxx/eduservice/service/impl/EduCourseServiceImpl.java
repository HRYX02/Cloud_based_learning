package com.sxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxx.eduservice.entity.EduCourse;
import com.sxx.eduservice.entity.EduCourseDescription;
import com.sxx.eduservice.entity.vo.background.CourseInfoVo;
import com.sxx.eduservice.entity.vo.background.CoursePublishVO;
import com.sxx.eduservice.entity.vo.reception.CourseVo;
import com.sxx.eduservice.mapper.EduCourseMapper;
import com.sxx.eduservice.service.EduChapterService;
import com.sxx.eduservice.service.EduCourseDescriptionService;
import com.sxx.eduservice.service.EduCourseService;
import com.sxx.exceptionhandler.YunShangException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private EduVideoServiceImpl videoService;
    @Autowired
    private EduChapterService chapterService;

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
        eduCourse.setIsDeleted(0);
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

    /**
     * @description 查询课程基本信息
     */
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

    /**
     * @description 修改课程基本信息
     */
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

    /**
     * @description 获取最终确认的所有信息
     */
    @Override
    public CoursePublishVO publishCourseInfo(String id) {
        CoursePublishVO publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    /**
     * @description 删除课程
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeCourse(String courseId) {
        // 1 根据课程ID删除小节
        videoService.removeVideoCourseId(courseId);

        // 2 根据课程ID删除章节
        chapterService.removeChapterByCourseId(courseId);

        // 3 根据课程ID删除描述
        courseDescriptionService.removeById(courseId);

        // 4 根据课程ID删除课程本身
        boolean remove = this.removeById(courseId);
        if (!remove) {
            throw new YunShangException(20001,"删除失败");
        }
    }

    @Override
    public Map<String, Object> getCourseList(Page<EduCourse> pageInfo, CourseVo courseVo) {
        LambdaQueryWrapper<EduCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(courseVo.getSubjectParentId()), EduCourse::getSubjectParentId, courseVo.getSubjectParentId());
        queryWrapper.eq(StringUtils.isNotEmpty(courseVo.getSubjectId()), EduCourse::getSubjectId, courseVo.getSubjectId());
        queryWrapper.orderByDesc(StringUtils.isNotEmpty(courseVo.getBuyCountSort()), EduCourse::getBuyCount);
        queryWrapper.orderByDesc(StringUtils.isNotEmpty(courseVo.getGmtCreateSort()), EduCourse::getGmtCreate);
        queryWrapper.orderByDesc(StringUtils.isNotEmpty(courseVo.getPriceSort()), EduCourse::getPrice);

       this.page(pageInfo, queryWrapper);

        List<EduCourse> records = pageInfo.getRecords();
        long current = pageInfo.getCurrent();
        long pages = pageInfo.getPages();
        long size = pageInfo.getSize();
        long total = pageInfo.getTotal();
        boolean hasNext = pageInfo.hasNext();
        boolean hasPrevious = pageInfo.hasPrevious();

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }
}
