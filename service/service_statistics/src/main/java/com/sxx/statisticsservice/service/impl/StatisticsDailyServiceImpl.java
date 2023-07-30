package com.sxx.statisticsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxx.commonutils.R;
import com.sxx.statisticsservice.client.UcenterClient;
import com.sxx.statisticsservice.entity.StatisticsDaily;
import com.sxx.statisticsservice.mapper.StatisticsDailyMapper;
import com.sxx.statisticsservice.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author SxxStar
 * @since 2023-07-29
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient uCenterClient;

    @Override
    public void registerCount(String day) {
        // 添加记录之前删除表相同日期的数据
        LambdaQueryWrapper<StatisticsDaily> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StatisticsDaily::getDateCalculated, day);
        this.remove(queryWrapper);

        // 远程调用得到某一天注册人数
        R registerR = uCenterClient.countRegister(day);
        Integer countRegister = (Integer) registerR.getData().get("countRegister");

        // 把获取数据添加数据库，统计分析表里面
        StatisticsDaily sta = new StatisticsDaily();
        // 注册人数
        sta.setRegisterNum(countRegister);
        // 统计日期
        sta.setDateCalculated(day);

        // TODO 随机生成，后期可以自己完善
        sta.setVideoViewNum(RandomUtils.nextInt(100, 200));
        sta.setLoginNum(RandomUtils.nextInt(100, 200));
        sta.setCourseNum(RandomUtils.nextInt(100, 200));
        this.save(sta);
    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        // 根据条件查询对应数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated", begin, end);
        wrapper.select("date_calculated", type);
        List<StatisticsDaily> statisticsDailyList = this.list(wrapper);

        /*
         * 因为返回有两部分数据：日期 和 日期对应数量
         * 前端要求数组json结构，对应后端java代码是list集合
         * 创建两个list集合，一个日期list，一个数量list
         */
        List<String> date_calculatedList = new ArrayList<>();
        List<Integer> numDataList = new ArrayList<>();

        // 遍历查询所有数据list集合，进行封装
        for (int i = 0; i < statisticsDailyList.size(); i++) {
            StatisticsDaily daily = statisticsDailyList.get(i);
            // 封装日期list集合
            date_calculatedList.add(daily.getDateCalculated());
            // 封装对应数量
            switch (type) {
                case "login_num":
                    numDataList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }

        // 把封装之后两个list集合放到map集合，进行返回
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList", date_calculatedList);
        map.put("numDataList", numDataList);
        return map;
    }
}
