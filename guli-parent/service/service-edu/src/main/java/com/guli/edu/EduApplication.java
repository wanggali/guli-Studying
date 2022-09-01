package com.guli.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: gali
 * @Date: 2022-09-01 15:30
 * @Description:
 */
@ComponentScan(basePackages = {"com.guli"}) //扫描所有的依赖及配置文件(包扫描规则)
@SpringBootApplication
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
