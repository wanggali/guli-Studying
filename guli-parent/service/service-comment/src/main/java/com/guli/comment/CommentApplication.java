package com.guli.comment;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: gali
 * @Date: 2022-09-16 14:45
 * @Description:
 */
@SpringBootApplication
@ComponentScan({"com.guli"})
@EnableDiscoveryClient
public class CommentApplication {
}
