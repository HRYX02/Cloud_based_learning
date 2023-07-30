package com.sxx.statisticsservice.schedule;

import com.sxx.statisticsservice.service.StatisticsDailyService;
import com.sxx.statisticsservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author SHIXINXI
 * @description 定时任务类
 * @create 2023-07-30-下午 2:38
 */

@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * @description 在每天凌晨1点把前一天数据进行数据查询添加
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}