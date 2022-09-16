package com.guli.statistics.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: gali
 * @Date: 2022-09-16 21:32
 * @Description:
 */
@Configuration
@MapperScan("com.guli.statistics.mapper")
public class StatisticsConfig {
}
