package com.sxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxx.eduservice.entity.EduTeacher;
import com.sxx.eduservice.mapper.EduTeacherMapper;
import com.sxx.eduservice.service.EduTeacherService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author SxxStar
 * @since 2023-05-31
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Map<String, Object> getTeacherList(Page<EduTeacher> pageInfo) {
        LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(EduTeacher::getId);
        this.page(pageInfo,queryWrapper);

        List<EduTeacher> records = pageInfo.getRecords();
        long current = pageInfo.getCurrent();
        long pages = pageInfo.getPages();
        long size = pageInfo.getSize();
        long total = pageInfo.getTotal();
        boolean hasNext = pageInfo.hasNext();
        boolean hasPrevious = pageInfo.hasPrevious();

        // 把分页数据获取出来，放到map集合
        Map<String,Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }
}
