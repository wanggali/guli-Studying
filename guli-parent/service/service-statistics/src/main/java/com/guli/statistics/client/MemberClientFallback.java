package com.guli.statistics.client;

import com.guli.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auther: gali
 * @Date: 2022-09-16 22:00
 * @Description:
 */
@Component
@Slf4j
public class MemberClientFallback implements MemberClient{
    @Override
    public Result countRegister(String day) {
        return Result.error().message("调用查询每天注册人数失败！！！");
    }
}
