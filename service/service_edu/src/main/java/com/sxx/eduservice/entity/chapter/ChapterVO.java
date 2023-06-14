package com.sxx.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SHIXINXI
 * @description 章节VO
 * @create 2023-06-14-13:57
 */

@Data
public class ChapterVO {
    private String id;

    private String title;

    /**
     * @description 表示小节
     */
    private List<VideoVO> children = new ArrayList<>();
}
