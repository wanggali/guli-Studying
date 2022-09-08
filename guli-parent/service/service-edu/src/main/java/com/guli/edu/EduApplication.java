package com.guli.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: gali
 * @Date: 2022-09-01 15:30
 * @Description:
 */
@ComponentScan(basePackages = {"com.guli"}) //扫描所有的依赖及配置文件(包扫描规则)
@SpringBootApplication
@EnableDiscoveryClient//nacos注册
@EnableFeignClients //openfenign服务调用开启
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
