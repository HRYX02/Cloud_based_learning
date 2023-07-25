package com.sxx.eduservice.entity.vo.reception;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author SHIXINXI
 * @description 前台课程页面条件对象
 * @create 2023-07-25-上午 12:28
 */
@Data
public class CourseReceptionQuery {
    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    private String subjectId;

    @ApiModelProperty(value = "销量排序")
    private String buyCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreateSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;
}