package com.sxx.eduservice.controller.background;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxx.commonutils.R;
import com.sxx.eduservice.entity.vo.TeacherQuery;
import com.sxx.eduservice.entity.EduTeacher;
import com.sxx.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 讲师 前端控制器
 * @Api Controller名称
 * @author SxxStar
 * @since 2023-05-31
 */

@Api(description = "讲师管理")
@Slf4j
@CrossOrigin
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
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("teachers",list);
    }

    /**
     * @description 查询指定id的讲师
     */
    @ApiOperation(value = "查询指定id的讲师")
    @GetMapping("/getTeacher/{id}")
    public R getTeacher(@ApiParam(name = "id",value = "讲师ID") @PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }

    /**
     * @description 逻辑删除讲师的方法
     * @PathVariable 路径变量
     * @ApiParam 替换输入参数栏的原始Parameter和description
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师ID") @PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        if (teacher != null) {
            boolean flag = eduTeacherService.removeById(id);
            return R.ok();
        }
        return R.error();
    }

    /**
     * @description 分页查询的方法
     * @param current 当前页
     * @param limit 每页显示记录数
     */
    @ApiOperation(value = "分页查询讲师方法")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pagetListTeacher(@ApiParam(name = "current",value = "当前页") @PathVariable long current,
                              @ApiParam(name = "limit",value = "每页显示记录数") @PathVariable long limit) {

        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        eduTeacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        Map map = new HashMap<>();
        map.put("total",total);
        map.put("records",records);

        return R.ok().data(map);
    }

    /**
     * @description 条件查询讲师带分页的方法
     * required = false 表示该值可以没有(目前测验不写也行)
     * @RequestBody:只能使用PostMapping提交
     */
    @ApiOperation(value = "条件查询带分页的方法")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "current",value = "当前页") @PathVariable String current,
                                  @ApiParam(name = "limit",value = "每页显示记录数") @PathVariable String limit,
                                  @ApiParam(name = "teacherQuery",value = "条件") @RequestBody TeacherQuery teacherQuery) {

        long l = Long.parseLong(current);
        long l1 = Long.parseLong(limit);
        Page<EduTeacher> pageTeacher = new Page<>(l,l1);
        LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();

        // 多条件组合查询
        queryWrapper.like(StringUtils.isNotEmpty(teacherQuery.getName()),EduTeacher::getName,teacherQuery.getName());
        queryWrapper.eq(teacherQuery.getLevel() != null,EduTeacher::getLevel,teacherQuery.getLevel());
        queryWrapper.ge(StringUtils.isNotEmpty(teacherQuery.getBegin()),EduTeacher::getGmtCreate,teacherQuery.getBegin());
        queryWrapper.le(StringUtils.isNotEmpty(teacherQuery.getEnd()),EduTeacher::getGmtCreate,teacherQuery.getEnd());
        queryWrapper.orderByDesc(EduTeacher::getGmtCreate).orderByDesc(EduTeacher::getName);
        eduTeacherService.page(pageTeacher,queryWrapper);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * @description 添加讲师的接口方法
     */
    @ApiOperation(value = "添加讲师的接口方法")
    @PostMapping("/addTeacher")
    public R addTeacher(@ApiParam(name = "eduTeacher",value = "要添加的老师") @RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        }
        return R.error();
    }



    /**
     * @description 修改讲师的接口方法
     */
    @ApiOperation(value = "修改讲师的接口方法")
    @PostMapping("/updateTeacher")
    public R updateTeacher(@ApiParam(name = "eduTeacher",value = "要修改的老师") @RequestBody EduTeacher eduTeacher){
        boolean update = eduTeacherService.updateById(eduTeacher);
        if (update) {
            return R.ok();
        }
        return R.error();
    }
}

