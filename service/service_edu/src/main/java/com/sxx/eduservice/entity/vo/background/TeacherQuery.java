package com.sxx.eduservice.entity.vo.background;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author SHIXINXI
 * @description 条件查询vo类
 * @create 2023-06-03-14:45
 */

@Data
public class TeacherQuery {

    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    /**
     * @apiNote begin时间用String类型 前端传过来的数据无需进行类型转换
     */
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")

    private String begin;
    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
