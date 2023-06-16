package com.sxx.eduservice.entity.vo;

import lombok.Data;

/**
 * @author SHIXINXI
 * @description 课程发布页面VO
 * @create 2023-06-16-9:04
 */

@Data
public class CoursePublishVO {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
