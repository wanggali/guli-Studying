package com.guli.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: gali
 * @Date: 2022-09-16 21:31
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.guli"})
@EnableFeignClients
@EnableDiscoveryClient
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class,args);
    }
}
