package com.guli.statistics.controller;


import com.guli.statistics.client.MemberClient;
import com.guli.statistics.service.DailyService;
import com.guli.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author gali
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/statistics/daily")
@CrossOrigin
public class DailyController {

    @Resource
    private DailyService dailyService;

    /**
     * 统计某一天的注册人数
     */
    @PostMapping("/registerCount/{day}")
    public Result registerCount(@PathVariable String day){
        dailyService.registerCount(day);
        return Result.ok();
    }
    /**
     * 图标显示
     * 日期，数量
     */
    @GetMapping("/getShowData/{type}/{begin}/{end}")
    public Result getShowData(@PathVariable String type,
                              @PathVariable String begin,
                              @PathVariable String end){
        Map<String,Object> map=dailyService.getShowData(type,begin,end);
        return Result.ok().data(map);
    }
}

