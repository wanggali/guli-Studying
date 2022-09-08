package com.guli.edu.client;

import com.guli.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: gali
 * @Date: 2022-09-08 15:16
 * @Description:
 */
@FeignClient(value = "service-vod",fallback = VodClientFallbackClient.class)  //服务注册的名字
@Component
public interface VodClient {
    //定义方法调用的路径,@PathVariable一定指定参数名称
    @DeleteMapping("/vod/video/removeAlyVideo/{id}")
    Result removeAlyVideo(@PathVariable("id") String id);

    //删除多个视频
    @DeleteMapping("/vod/video/removeBatch")
    Result removeBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
