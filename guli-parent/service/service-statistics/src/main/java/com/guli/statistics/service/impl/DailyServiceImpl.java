package com.guli.statistics.service.impl;

import com.guli.statistics.client.MemberClient;
import com.guli.statistics.pojo.Daily;
import com.guli.statistics.mapper.DailyMapper;
import com.guli.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.utils.Result;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author gali
 * @since 2022-09-16
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Resource
    private MemberClient memberClient;
    @Resource
    private DailyMapper dailyMapper;
    @Override
    public void registerCount(String day) {
        Result result = memberClient.countRegister(day);
        Map<String, Object> data = result.getData();
        Integer countRegister = (Integer) data.get("countRegister");
        //获取的数据添加到数据库
        Daily sta = new Daily();
        sta.setRegisterNum(countRegister);//注册人数
        sta.setDateCalculated(day);        //注册时间

        sta.setVideoViewNum(RandomUtils.nextInt(100,200));
        sta.setLoginNum(RandomUtils.nextInt(100,200));
        sta.setCourseNum(RandomUtils.nextInt(100,200));
        dailyMapper.insert(sta);
    }
}
