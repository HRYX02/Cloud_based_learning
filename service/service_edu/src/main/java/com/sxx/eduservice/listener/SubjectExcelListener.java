package com.sxx.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sxx.eduservice.entity.EduSubject;
import com.sxx.eduservice.entity.excel.SubjectData;
import com.sxx.eduservice.service.EduSubjectService;
import com.sxx.exceptionhandler.YunShangException;

/**
 * @author SHIXINXI
 * @description easy-excel读取监听器
 * @create 2023-06-08-18:50
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;

    public SubjectExcelListener() {}

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.subjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new YunShangException(20001,"文件项目为空");
        }
        // 一行一行读取，每次读取有两个值，第一个值为一级分类，第二个值为二级分类
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        // 判断一级分类是否重复
        if (existOneSubject == null) {
            // 没有相同的一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        String pid = existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        // 判断二级分类是否重复
        if (existTwoSubject == null) {
            // 没有相同的二级分类，进行添加
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /**
     * @description 判断一级分类不能重复添加
     */
    private EduSubject existOneSubject(EduSubjectService subjectService,String name) {
        LambdaQueryWrapper<EduSubject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduSubject::getTitle,name);
        queryWrapper.eq(EduSubject::getParentId,0);
        EduSubject eduSubject = subjectService.getOne(queryWrapper);
        return eduSubject;
    }

    /**
     * @description 判断二级分类不能重复添加
     */
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid) {
        LambdaQueryWrapper<EduSubject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduSubject::getTitle,name);
        queryWrapper.eq(EduSubject::getParentId,pid);
        EduSubject eduSubject = subjectService.getOne(queryWrapper);
        return eduSubject;
    }
}
