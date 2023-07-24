package com.sxx.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxx.eduservice.entity.EduTeacher;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author SxxStar
 * @since 2023-05-31
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherList(Page<EduTeacher> pageInfo);
}
