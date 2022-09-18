package com.guli.statistics.schedule;

import com.guli.statistics.service.DailyService;
import com.guli.utils.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Auther: gali
 * @Date: 2022-09-18 17:03
 * @Description:
 */
@Component
public class ScheduledTask {

    @Resource
    private DailyService dailyService;

    /**
     * 在每天凌晨的一点执行方法
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduledAddLogs() {
        //DateUtil工具类生成前一天时间
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        dailyService.registerCount(day);
    }
}
