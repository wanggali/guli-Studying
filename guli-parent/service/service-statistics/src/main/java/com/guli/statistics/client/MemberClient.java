package com.guli.statistics.client;

import com.guli.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: gali
 * @Date: 2022-09-16 21:41
 * @Description:
 */
@Component
@FeignClient(name = "service-ucenter",fallback = MemberClientFallback.class)
public interface MemberClient {
    @GetMapping("/center/member/countRegister/{day}")
    public Result countRegister(@PathVariable("day") String day);
}
