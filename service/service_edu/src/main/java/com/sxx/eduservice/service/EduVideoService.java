package com.sxx.eduservice.service;

import com.sxx.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author SxxStar
 * @since 2023-06-12
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoCourseId(String courseId);
    void removeSubsectionAndVideo(String id);
}
