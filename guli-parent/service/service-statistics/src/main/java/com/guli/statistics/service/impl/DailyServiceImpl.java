package com.guli.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.statistics.client.MemberClient;
import com.guli.statistics.pojo.Daily;
import com.guli.statistics.mapper.DailyMapper;
import com.guli.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.utils.Result;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        //添加数据之前，先删除当天的数据再添加
        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        dailyMapper.delete(wrapper);
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

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        //根据条件查询对应的数据
        QueryWrapper<Daily> wrapper= new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);
        List<Daily> staList = dailyMapper.selectList(wrapper);
        List<String> dateList=new ArrayList<>();
        List<Integer> numList=new ArrayList<>();
        //遍历查询出来的所有数据
        for (int i = 0; i < staList.size(); i++) {
            Daily daily = staList.get(i);
            String date = daily.getDateCalculated();
            dateList.add(date);
            if (type.equals("login_num")){//封装数量类型
                numList.add(daily.getLoginNum());
            }
            if (type.equals("register_num")){
                numList.add(daily.getRegisterNum());
            }
            if (type.equals("video_view_num")){
                numList.add(daily.getVideoViewNum());
            }
            if (type.equals("course_num")){
                numList.add(daily.getCourseNum());
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("dateList",dateList);
        map.put("typeList",numList);
        return map;
    }
}
