package com.sxx.eduservice.controller.background;


import com.sxx.commonutils.R;
import com.sxx.eduservice.entity.subject.OneSubject;
import com.sxx.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description 课程科目 前端控制器
 * @author SxxStar
 * @since 2023-06-08
 */
@Api(description = "课程科目")
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * @description 添加课程分类
     */
    @ApiOperation("添加课程分类")
    @PostMapping("/addSubject")
    public R addSubject(@ApiParam(name = "multipartFile",value = "excel文件") @RequestParam("multipartFile") MultipartFile file) {
        eduSubjectService.saveSubject(eduSubjectService,file);
        return R.ok();
    }

    /**
     *  @description 课程分类列表(树形)
     */
    @ApiOperation("课程分类列表(树形)")
    @GetMapping("/getAllSubject")
    public R getAllSubject() {
        List<OneSubject> allOneTwoSubject = eduSubjectService.getAllOneTwoSubject();
        return R.ok().data("list",allOneTwoSubject);
    }
}

