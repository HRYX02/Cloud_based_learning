package com.sxx.statisticsservice.controller;


import com.sxx.commonutils.R;
import com.sxx.statisticsservice.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author SxxStar
 * @since 2023-07-29
 */
@Api(description = "网站统计日数据")
@RestController
@CrossOrigin
@RequestMapping("/statisticsservice/sta")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;


    /**
     * @description 统计某一天注册人数,生成统计数据
     * @param day
     * @return
     */
    @ApiOperation("统计某一天注册人数")
    @PostMapping("/registerCount/{day}")
    public R registerCount(@PathVariable @ApiParam(name = "day",value = "时间") String day) {
        statisticsDailyService.registerCount(day);
        return R.ok();
    }


    /**
     * @description 图表显示，返回两部分数据，日期json数组，数量json数组
     * @param type
     * @param begin
     * @param end
     * @return
     */
    @ApiOperation("图表显示")
    @GetMapping("/showData/{type}/{begin}/{end}")
    public R showData(@PathVariable @ApiParam(name = "type",value="类型") String type,
                      @PathVariable @ApiParam(name = "begin",value="开始时间") String begin,
                      @PathVariable @ApiParam(name = "end",value="结束时间") String end) {
        Map<String,Object> map = statisticsDailyService.getShowData(type,begin,end);
        return R.ok().data(map);
    }

}