package com.guli.canal;

import com.guli.canal.client.CanalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @Auther: gali
 * @Date: 2022-09-19 14:46
 * @Description:
 */
@SpringBootApplication
public class CanalApplication implements CommandLineRunner {
    @Resource
    private CanalClient client;

    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        //项目启动，执行canal客户端监听
        client.run();
    }
}
