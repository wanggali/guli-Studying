package com.guli.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: gali
 * @Date: 2022-09-16 16:04
 * @Description:
 */
@Configuration
@MapperScan("com.guli.order.mapper")
public class OrderConfig {
}
