package com.sxx.eduservice.controller.background;


import com.sxx.commonutils.R;
import com.sxx.eduservice.entity.EduChapter;
import com.sxx.eduservice.entity.chapter.ChapterVO;
import com.sxx.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author SxxStar
 * @since 2023-06-12
 */
@Slf4j
@Api(description = "课程大纲")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    /**
     * @description 课程大纲列表,根据课程id进行查询
     */
    @ApiOperation(value = "课程大纲")
    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(@ApiParam("课程ID") @PathVariable String courseId) {
        List<ChapterVO> list =  chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }

    /**
     * @description 添加章节
     */
    @ApiOperation("添加章节")
    @PostMapping("/addChapter")
    public R addChapter(@ApiParam("章节信息") @RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return R.ok();
    }

    /**
     * @description 根据id查询章节
     */
    @ApiOperation("根据id查询章节")
    @GetMapping("/getChapterInfo/{chapterId}")
    public R getChapterInfo(@ApiParam("章节ID") @PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }

    /**
     * @description 修改章节
     */
    @ApiOperation("修改章节")
    @PostMapping("/updateChapter")
    public R updateChapter(@ApiParam("章节信息") @RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return R.ok();
    }

    /**
     * @description 删除章节
     */
    @ApiOperation("删除章节")
    @DeleteMapping("/{chapterId}")
    public R deleteChapter(@ApiParam("章节ID") @PathVariable String chapterId) {
        boolean deleteChapter = chapterService.deleteChapter(chapterId);
        if (deleteChapter) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

