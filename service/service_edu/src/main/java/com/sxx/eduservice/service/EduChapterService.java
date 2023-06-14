package com.sxx.eduservice.service;

import com.sxx.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxx.eduservice.entity.chapter.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author SxxStar
 * @since 2023-06-12
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVO> getChapterVideoByCourseId(String courseId);
}
