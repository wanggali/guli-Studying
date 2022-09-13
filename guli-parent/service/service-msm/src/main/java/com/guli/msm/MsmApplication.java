package com.guli.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: gali
 * @Date: 2022-09-13 17:45
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.guli"})
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class,args);
    }
}
