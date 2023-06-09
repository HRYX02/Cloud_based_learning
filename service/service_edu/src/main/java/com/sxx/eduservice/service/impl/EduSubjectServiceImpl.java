package com.sxx.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxx.eduservice.entity.EduSubject;
import com.sxx.eduservice.entity.excel.SubjectData;
import com.sxx.eduservice.entity.subject.OneSubject;
import com.sxx.eduservice.entity.subject.TwoSubject;
import com.sxx.eduservice.listener.SubjectExcelListener;
import com.sxx.eduservice.mapper.EduSubjectMapper;
import com.sxx.eduservice.service.EduSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 课程科目
 * @author SxxStar
 * @since 2023-06-08
 */
@Slf4j
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

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        // 查询所有一级分类
        LambdaQueryWrapper<EduSubject> oneQuery = new LambdaQueryWrapper<>();
        oneQuery.eq(EduSubject::getParentId,0);
        List<EduSubject> oneSubjectList = this.list(oneQuery);
        // 查询所有二级分类
        LambdaQueryWrapper<EduSubject> twoQuery = new LambdaQueryWrapper<>();
        twoQuery.ne(EduSubject::getParentId, 0);
        List<EduSubject> twoSubjectList = this.list(twoQuery);

        // 创建List集合，用于储存最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();
        /*
         * 封装一级分类
         * 查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值，
         * 封装到要求的list集合里面 -> List<OneSubject> finalSubjectList
         */
        for (int i = 0; i < oneSubjectList.size(); i++) {
            // 得到oneSubjectList每个eduSubject对象
            EduSubject eduSubject = oneSubjectList.get(i);
            // 把eduSubject里面值获取出来，放到OneSubject对象里面
            OneSubject oneSubject = new OneSubject();
            /* 复杂
             * oneSubject.setId(eduSubject.getId());
             * oneSubject.setTitle(eduSubject.getTitle());
             */
            // 简单
            BeanUtils.copyProperties(eduSubject,oneSubject);
            // 多个OneSubject放到finalSubjectList里面
            finalSubjectList.add(oneSubject);

            // 封装二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //遍历二级分类list集合
            for (int j = 0; j < twoSubjectList.size(); j++) {
                EduSubject twoEduSubject = twoSubjectList.get(j);
                if (twoEduSubject.getParentId().equals(oneSubject.getId())) {
                    //把tSubject值复制到TwoSubject里面，放到twoFinalSubjectList里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoEduSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);
        }

        return finalSubjectList;
    }
}
