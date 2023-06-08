package com.sxx.eduservice.service;

import com.sxx.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

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
}
