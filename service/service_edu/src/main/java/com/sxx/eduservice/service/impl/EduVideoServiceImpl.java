package com.sxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sxx.eduservice.entity.EduVideo;
import com.sxx.eduservice.mapper.EduVideoMapper;
import com.sxx.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author SxxStar
 * @since 2023-06-12
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    /**
     * @description 删除小节
     * TODO 视频文件对应删除
     * @param courseId 课程ID
     */
    @Override
    public void removeVideoCourseId(String courseId) {
        LambdaQueryWrapper<EduVideo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduVideo::getCourseId,courseId);
        this.remove(queryWrapper);
    }
}
