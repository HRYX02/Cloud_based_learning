package com.sxx.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxx.eduservice.entity.EduSubject;
import com.sxx.eduservice.entity.excel.SubjectData;
import com.sxx.eduservice.listener.SubjectExcelListener;
import com.sxx.eduservice.mapper.EduSubjectMapper;
import com.sxx.eduservice.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description 课程科目
 * @author SxxStar
 * @since 2023-06-08
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * @description 添加课程分类
     */
    @Override
    public void saveSubject(EduSubjectService eduSubjectService,MultipartFile file) {
        try {
            // 1 得到文件的输入流
            InputStream inputStream = file.getInputStream();
            // 2 调用方法进行读取
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
