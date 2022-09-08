package com.guli.edu.client;

import com.guli.utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: gali
 * @Date: 2022-09-08 16:53
 * @Description:
 */
@Component
public class VodClientFallbackClient implements VodClient{
    @Override
    public Result removeAlyVideo(String id) {
        return Result.error().message("删除视频出错");
    }

    @Override
    public Result removeBatch(List<String> videoIdList) {
        return Result.error().message("删除多个视频出错");
    }
}
