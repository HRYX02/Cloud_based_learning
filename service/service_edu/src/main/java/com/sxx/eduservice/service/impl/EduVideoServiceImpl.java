package com.sxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxx.commonutils.R;
import com.sxx.eduservice.client.VodClient;
import com.sxx.eduservice.entity.EduVideo;
import com.sxx.eduservice.mapper.EduVideoMapper;
import com.sxx.eduservice.service.EduVideoService;
import com.sxx.exceptionhandler.YunShangException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private VodClient vodClient;

    /**
     * @description 删除课程，阿里云视频文件对应删除
     * @param courseId 课程ID
     */
    @Override
    public void removeVideoCourseId(String courseId) {
        //1 根据课程id查询课程所有的视频id
        LambdaQueryWrapper<EduVideo> wrapperVideo = new LambdaQueryWrapper<>();
        wrapperVideo.eq(EduVideo::getCourseId,courseId);
        wrapperVideo.select(EduVideo::getVideoSourceId);
        List<EduVideo> eduVideoList = this.list(wrapperVideo);

        // List<EduVideo>变成List<String>
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < eduVideoList.size(); i++) {
            EduVideo eduVideo = eduVideoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(StringUtils.isNotEmpty(videoSourceId)) {
                //放到videoIds集合里面
                videoIds.add(videoSourceId);
            }
        }

        //根据多个视频id删除多个视频
        if(videoIds.size()>0) {
            vodClient.deleteBatch(videoIds);
        }

        LambdaQueryWrapper<EduVideo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduVideo::getCourseId,courseId);
        this.remove(queryWrapper);
    }

    /**
     * @description 删除小节视频文件对应删除
     * @param id 小节ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeSubsectionAndVideo(String id) {
        EduVideo video = this.getById(id);
        String videoSourceId = video.getVideoSourceId();
        // 判断小节中是否有视频ID
        if (StringUtils.isNotEmpty(videoSourceId)) {
            R result = vodClient.removeAlYunVideo(video.getVideoSourceId());
            if (result.getCode() == 20001){
                throw new YunShangException(20001,"删除视频失败，熔断器...");
            }
        }
        this.removeById(id);
    }

}
