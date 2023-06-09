package com.sxx.eduservice.service;

import com.sxx.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxx.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author SxxStar
 * @since 2023-06-08
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(EduSubjectService eduSubjectService,MultipartFile file);

    /**
     * @description 得到所有的课程分类
     */
    List<OneSubject> getAllOneTwoSubject();
}
