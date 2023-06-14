package com.sxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxx.eduservice.entity.EduChapter;
import com.sxx.eduservice.entity.EduVideo;
import com.sxx.eduservice.entity.chapter.ChapterVO;
import com.sxx.eduservice.entity.chapter.VideoVO;
import com.sxx.eduservice.mapper.EduChapterMapper;
import com.sxx.eduservice.service.EduChapterService;
import com.sxx.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author SxxStar
 * @since 2023-06-12
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVO> getChapterVideoByCourseId(String courseId) {

        // 1 根据课程id查询课程里面所有得章节
        LambdaQueryWrapper<EduChapter> queryWrapperChapter = new LambdaQueryWrapper<>();
        queryWrapperChapter.eq(EduChapter::getCourseId,courseId);
        List<EduChapter> eduChapterList = this.list(queryWrapperChapter);

        // 2 根据课程id查询课程里面所有得小节
        LambdaQueryWrapper<EduVideo> queryWrapperVideo = new LambdaQueryWrapper<>();
        queryWrapperVideo.eq(EduVideo::getCourseId,courseId);
        List<EduVideo> eduVideoList = videoService.list(queryWrapperVideo);

        List<ChapterVO> finalList = new ArrayList<>();
        // 3 遍历查询章节list集合进行封装
        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(eduChapter,chapterVO);
            finalList.add(chapterVO);

            // 4 遍历查询小节list集合进行封装
            List<VideoVO> videoList = new ArrayList<>();
            for (int j = 0; j < eduVideoList.size(); j++) {
                EduVideo eduVideo = eduVideoList.get(j);
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVO videoVO = new VideoVO();
                    BeanUtils.copyProperties(eduVideo,videoVO);
                    videoList.add(videoVO);
                }
            }
            chapterVO.setChildren(videoList);
        }
        return finalList;
    }
}
