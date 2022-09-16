package com.guli.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: gali
 * @Date: 2022-09-16 20:41
 * @Description:
 */
@Component
@FeignClient(name = "service-order",fallback = OrderClientFallbackClient.class)
public interface OrderClient {

    @GetMapping("/order/isBuyCourse/{courseId}/{memberId}")
    public boolean  isBuyCourse(@PathVariable("courseId") String courseId,
                                @PathVariable("memberId") String memberId);
}
