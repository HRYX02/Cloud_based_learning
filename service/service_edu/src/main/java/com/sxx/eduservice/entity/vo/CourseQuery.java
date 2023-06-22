package com.sxx.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author SHIXINXI
 * @description 条件查询vo类
 * @create 2023-06-03-14:45
 */

@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程名称,模糊查询")
    private String title;

    @ApiModelProperty(value = "发布情况")
    private String status;
}
