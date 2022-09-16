package com.guli.statistics.service;

import com.guli.statistics.pojo.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author gali
 * @since 2022-09-16
 */
public interface DailyService extends IService<Daily> {

    void registerCount(String day);
}
