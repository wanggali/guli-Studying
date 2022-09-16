package com.guli.edu.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auther: gali
 * @Date: 2022-09-16 20:46
 * @Description:
 */
@Component
@Slf4j
public class OrderClientFallbackClient implements OrderClient {
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        log.error("查询订单状态失败！！！！");
        return false;
    }
}
