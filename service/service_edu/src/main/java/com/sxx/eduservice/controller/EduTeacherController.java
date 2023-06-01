package com.sxx.eduservice.controller;

import com.sxx.eduservice.entity.EduTeacher;
import com.sxx.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 讲师 前端控制器
 * @Api Controller名称
 * @author SxxStar
 * @since 2023-05-31
 */

@Api(description = "讲师管理")
@Slf4j
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;
    /**
     * @description 查询讲师表中的所有数据
     * @ApiOperation 替换swagger中每个mapping右上角的方法名
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public List<EduTeacher> findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }
    /**
     * @description 逻辑删除讲师的方法
     * @PathVariable 路径变量
     * @ApiParam 替换输入参数栏的原始Parameter和description
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public boolean removeTeacher(@ApiParam(name = "id",value = "讲师ID") @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        return flag;
    }

}

